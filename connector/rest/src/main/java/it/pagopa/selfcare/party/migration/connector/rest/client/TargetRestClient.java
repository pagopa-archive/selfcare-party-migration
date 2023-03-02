package it.pagopa.selfcare.party.migration.connector.rest.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "${rest-client.target.serviceCode}", url = "${rest-client.target.base-url}")
public interface TargetRestClient {
}
