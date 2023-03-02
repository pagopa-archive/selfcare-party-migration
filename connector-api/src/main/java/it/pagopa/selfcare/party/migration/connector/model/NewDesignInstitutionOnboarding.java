package it.pagopa.selfcare.party.migration.connector.model;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class NewDesignInstitutionOnboarding {
    private String productId;
    private RelationshipState status;
    private String tokenId;
    private String contract;
    private String pricingPlan;
    private Billing billing;
    private List<NewDesignInstitutionOnboardingSubProduct> subProducts;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
