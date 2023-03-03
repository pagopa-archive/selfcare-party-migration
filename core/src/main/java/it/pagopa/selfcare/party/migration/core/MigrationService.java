package it.pagopa.selfcare.party.migration.core;

import it.pagopa.selfcare.party.migration.core.model.MigrationReport;

public interface MigrationService {
    MigrationReport migrateUsers();
    MigrationReport migrateInstitutions();
    MigrationReport migrateTokens();
}
