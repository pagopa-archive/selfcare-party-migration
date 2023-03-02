package it.pagopa.selfcare.party.migration.connector.model;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class NewDesignInstitutionSubProduct {
    private String parentId;
    private String productId;
    private RelationshipState status;
    private String contract;
    private String pricingPlan;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
