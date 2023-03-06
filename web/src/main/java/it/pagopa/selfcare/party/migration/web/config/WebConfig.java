package it.pagopa.selfcare.party.migration.web.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.pagopa.selfcare.commons.web.config.BaseWebConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

@Configuration
@Import(BaseWebConfig.class)
class WebConfig {
    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    void init(){
        objectMapper.enable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
    }
}
