package it.pagopa.selfcare.party.migration.connector.model;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class NewDesignToken {
    private String id;
    private RelationshipState status;
    private String institutionId;
    private String productId;
    private OffsetDateTime expiringDate;
    private String checksum;
    private String contractTemplate;
    private String contractVersion;
    private String contractSigned;
    private List<NewDesignTokenUser> users;
    private InstitutionUpdate institutionUpdate;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
