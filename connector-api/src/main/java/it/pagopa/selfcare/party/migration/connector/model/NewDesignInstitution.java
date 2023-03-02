package it.pagopa.selfcare.party.migration.connector.model;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class NewDesignInstitution {
    private String id;
    private String externalId;
    private String origin;
    private String originId;
    private String description;
    private String institutionType;
    private String digitalAddress;
    private String address;
    private String zipCode;
    private String taxCode;
    private List<GeographicTaxonomy> geographicTaxonomies;
    private List<Attribute> attributes;
    private PaymentServiceProvider paymentServiceProvider;
    private DataProtectionOfficer dataProtectionOfficer;
    private String rea;
    private String shareCapital;
    private String businessRegisterPlace;
    private String supportEmail;
    private String supportPhone;
    private Boolean imported;
    private List<NewDesignInstitutionOnboarding> onboarding;
    private OffsetDateTime createdAt;
}
