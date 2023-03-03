package it.pagopa.selfcare.party.migration.connector.rest.config;

import it.pagopa.selfcare.commons.connector.rest.config.RestClientBaseConfig;
import it.pagopa.selfcare.party.migration.connector.rest.client.TargetRestClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Import(RestClientBaseConfig.class)
@EnableFeignClients(clients = TargetRestClient.class)
@PropertySource("classpath:config/target-rest-client.properties")
class TargetRestClientConfig {
}
