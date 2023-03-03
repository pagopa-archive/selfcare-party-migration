package it.pagopa.selfcare.party.migration.core.model;

import lombok.Value;

@Value
public class MigrationReport {
    long fetched;
    long processed;
    long successfulMigrated;
    boolean success;
}
