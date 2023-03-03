package it.pagopa.selfcare.party.migration.core;

import it.pagopa.selfcare.party.migration.connector.SourceDataConnectorService;
import it.pagopa.selfcare.party.migration.connector.TargetDataConnectorService;
import it.pagopa.selfcare.party.migration.connector.generated.NewDesignInstitution;
import it.pagopa.selfcare.party.migration.connector.generated.NewDesignToken;
import it.pagopa.selfcare.party.migration.connector.generated.NewDesignUser;
import it.pagopa.selfcare.party.migration.core.model.MigrationReport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

@Service
@Slf4j
class MigrationServiceImpl implements MigrationService {

    private final int sourcePageSize;
    private final int targetStoreParallelism;
    private final boolean stopOnError;
    private final SourceDataConnectorService sourceDataConnectorService;
    private final TargetDataConnectorService targetDataConnectorService;

    MigrationServiceImpl(
            @Value("${app.source.pageSize}") int sourcePageSize,
            @Value("${app.target.parallelism}") int targetStoreParallelism,
            @Value("${app.stopOnError}") boolean stopOnError,
            SourceDataConnectorService sourceDataConnectorService,
            TargetDataConnectorService targetDataConnectorService) {
        this.sourcePageSize = sourcePageSize;
        this.targetStoreParallelism = targetStoreParallelism;
        this.stopOnError = stopOnError;
        this.sourceDataConnectorService = sourceDataConnectorService;
        this.targetDataConnectorService = targetDataConnectorService;

        if (sourcePageSize <= 0) {
            throw new IllegalArgumentException("Cannot configure source page size to a value less or equal to 0");
        }
        if (targetStoreParallelism <= 0) {
            throw new IllegalArgumentException("Cannot configure target store parallelism to a value less or equal to 0");
        }
    }

    @Override
    public MigrationReport migrateUsers() {
        return migrate(
                "USERS",
                NewDesignUser::getId,
                sourceDataConnectorService::getUsers,
                targetDataConnectorService::saveUser,
                targetDataConnectorService::findUserById
        );
    }

    @Override
    public MigrationReport migrateInstitutions() {
        return migrate(
                "INSTITUTIONS",
                NewDesignInstitution::getId,
                sourceDataConnectorService::getInstitutions,
                targetDataConnectorService::saveInstitution,
                targetDataConnectorService::findInstitutionById
        );
    }

    @Override
    public MigrationReport migrateTokens() {
        return migrate(
                "TOKENS",
                NewDesignToken::getId,
                sourceDataConnectorService::getTokens,
                targetDataConnectorService::saveToken,
                targetDataConnectorService::findTokenById
        );
    }

    @SuppressWarnings({"squid:S2142","squid:S3776"})
    private <T> MigrationReport migrate(
            String flowName,
            Function<T, String> idGetter,
            BiFunction<Integer, Integer, List<T>> sourceRetriever,
            Consumer<T> targetPersister,
            Function<String, T> targetRetrieverById) {
        log.info("Starting migration of {}", flowName);

        int page = 0;
        AtomicBoolean result = new AtomicBoolean(true);

        long fetched = 0;
        AtomicLong processed = new AtomicLong(0L);
        AtomicLong successfulMigrated = new AtomicLong(0L);

        ForkJoinPool forkJoinPool = new ForkJoinPool(targetStoreParallelism);

        long startTime = System.currentTimeMillis();

        SecurityContext securityContext = SecurityContextHolder.getContext();

        while ((!stopOnError || result.get())) {
            List<T> sourceData = sourceRetriever.apply(page++, sourcePageSize);

            if (!sourceData.isEmpty()) {
                fetched += sourceData.size();
                ForkJoinTask<?> tasks = forkJoinPool.submit(() ->
                        sourceData.parallelStream()
                                .forEach(source -> {
                                    if (stopOnError && !result.get()) {
                                        return;
                                    }
                                    processed.incrementAndGet();
                                    SecurityContextHolder.setContext(securityContext);

                                    if (storeAndCheck(flowName, source, idGetter, targetPersister, targetRetrieverById)) {
                                        successfulMigrated.incrementAndGet();
                                    } else {
                                        result.compareAndSet(true, false);
                                    }
                                }));

                try {
                    tasks.get(5, TimeUnit.MINUTES);
                } catch (InterruptedException | ExecutionException | TimeoutException e) {
                    throw new IllegalStateException(String.format("Something gone wrong while waiting for store operations of page %d", page), e);
                }
            }

            if (sourceData.size() != sourcePageSize) {
                break;
            }
        }

        forkJoinPool.shutdown();

        MigrationReport report = new MigrationReport(fetched, processed.get(), successfulMigrated.get(), result.get());

        log.info("Migration of {} completed in {} ms: {}",
                flowName,
                System.currentTimeMillis() - startTime,
                report);

        return report;
    }

    private <T> boolean storeAndCheck(
            String flowName,
            T source,
            Function<T, String> idGetter,
            Consumer<T> targetPersister,
            Function<String, T> targetRetrieverById) {
        try {
            String id = idGetter.apply(source);
            targetPersister.accept(source);

            T target = targetRetrieverById.apply(id);
            if (!target.equals(source)) {
                log.error("Stored {} doesn't match with source:\nsource: {}\ntarget: {}",
                        flowName,
                        source.toString().replace('\n', ' '),
                        target.toString().replace('\n', ' '));
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            log.error("Something gone wrong while storing {} source: {}",
                    flowName, source, e);
            return false;
        }
    }
}
