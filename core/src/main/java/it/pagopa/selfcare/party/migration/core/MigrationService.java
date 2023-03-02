package it.pagopa.selfcare.party.migration.core;

public interface MigrationService {
    boolean migrateUsers();
    boolean migrateInstitutions();
    boolean migrateTokens();
}
