package it.pagopa.selfcare.party.migration.connector.rest.config;

import it.pagopa.selfcare.commons.connector.rest.config.RestClientBaseConfig;
import it.pagopa.selfcare.party.migration.connector.rest.client.SourceRestClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Import(RestClientBaseConfig.class)
@EnableFeignClients(clients = SourceRestClient.class)
@PropertySource("classpath:config/source-rest-client.properties")
class SourceRestClientConfig {
}
