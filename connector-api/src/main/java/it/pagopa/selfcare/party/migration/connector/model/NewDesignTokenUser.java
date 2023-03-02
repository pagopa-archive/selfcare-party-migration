package it.pagopa.selfcare.party.migration.connector.model;

import lombok.Data;

@Data
public class NewDesignTokenUser {
    private String userId;
    private String relationshipId;
    private PartyRole role;
}
