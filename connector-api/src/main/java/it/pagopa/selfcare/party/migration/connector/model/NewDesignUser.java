package it.pagopa.selfcare.party.migration.connector.model;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class NewDesignUser {
    private String id;
    private List<NewDesignUserInstitution> bindings;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
