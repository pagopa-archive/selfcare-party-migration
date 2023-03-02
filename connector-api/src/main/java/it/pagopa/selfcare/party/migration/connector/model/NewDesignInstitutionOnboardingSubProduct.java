package it.pagopa.selfcare.party.migration.connector.model;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class NewDesignInstitutionOnboardingSubProduct {
    private String parentId;
    private String productId;
    private RelationshipState status;
    private String tokenId;
    private String contract;
    private String pricingPlan;
    private Billing billing;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
