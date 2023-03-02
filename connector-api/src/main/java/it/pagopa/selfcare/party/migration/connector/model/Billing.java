package it.pagopa.selfcare.party.migration.connector.model;

import lombok.Data;

@Data
public class Billing {
    private String vatNumber;
    private String recipientCode;
    private Boolean publicServices;
}
