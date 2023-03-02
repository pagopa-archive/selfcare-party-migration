package it.pagopa.selfcare.party.migration.web.controller;

import it.pagopa.selfcare.commons.web.security.JwtAuthenticationStrategy;
import it.pagopa.selfcare.commons.web.security.JwtAuthenticationStrategyFactory;
import it.pagopa.selfcare.commons.web.security.JwtAuthenticationToken;
import it.pagopa.selfcare.party.migration.connector.rest.client.SourceRestClient;
import it.pagopa.selfcare.party.migration.connector.rest.client.TargetRestClient;
import it.pagopa.selfcare.utils.YamlPropertySourceFactory;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(
        classes = {
                PartyMigrationControllerIntegrationTest.TestConfig.class
        },
        properties = {
                "app.source.pageSize:1",
                "app.target.parallelism:1",

                // wiremock configuration
                "rest-client.source.base-url=http://localhost:${wiremock.server.port}/source",
                "rest-client.target.base-url=http://localhost:${wiremock.server.port}/target",
        }
)
@AutoConfigureWireMock(stubs = "classpath:/wiremockStubs", port = 0)
@AutoConfigureMockMvc
class PartyMigrationControllerIntegrationTest {

    @Configuration
    @EnableAutoConfiguration
    @ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class)
    @PropertySource(value = "file:../app/src/main/resources/config/application.yml", factory = YamlPropertySourceFactory.class)
    @ComponentScan("it.pagopa.selfcare.party.migration")
    public static class TestConfig {
    }

    @Autowired
    private MockMvc mvc;

    @SpyBean
    private SourceRestClient sourceRestClientSpy;
    @SpyBean
    private TargetRestClient targetRestClientSpy;

    @MockBean
    private JwtAuthenticationStrategyFactory authMock;

    @BeforeEach
    void mockAuth(){
        JwtAuthenticationStrategy authStratetegyMock = Mockito.mock(JwtAuthenticationStrategy.class);
        Mockito.when(authStratetegyMock.authenticate(Mockito.any()))
                        .thenAnswer(i -> new JwtAuthenticationToken(i.getArgument(0, JwtAuthenticationToken.class).getCredentials()));

        Mockito.when(authMock.create(Mockito.any()))
                .thenReturn(authStratetegyMock);
    }

    @AfterEach
    void verifyNoMoreInteractions(){
        Mockito.verifyNoMoreInteractions(sourceRestClientSpy, targetRestClientSpy);
    }

    @Test
    void testInstitutionMigration() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/migration/institutions")
                        .header("Authorization", "Bearer AUTHTOKEN")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().string("OK"));

        Mockito.verify(sourceRestClientSpy)
                .findInstitutions(0,1);
        Mockito.verify(sourceRestClientSpy)
                .findInstitutions(1,1);
        Mockito.verify(sourceRestClientSpy)
                .findInstitutions(2,1);

        verifyInstitutionOnTarget("INSTITUTIONID__0");

        verifyInstitutionOnTarget("INSTITUTIONID__1");
    }

    private void verifyInstitutionOnTarget(String institutionId) {
        Mockito.verify(targetRestClientSpy)
                .saveInstitution(Mockito.argThat(i -> institutionId.equals(i.getId())));
        Mockito.verify(targetRestClientSpy)
                .findInstitutionById(institutionId);
    }

    @Test
    void testUserMigration() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/migration/users")
                        .header("Authorization", "Bearer AUTHTOKEN")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().string("OK"));

        Mockito.verify(sourceRestClientSpy)
                .findUsers(0,1);
        Mockito.verify(sourceRestClientSpy)
                .findUsers(1,1);

        verifyUserOnTarget("USERID__0");
    }

    private void verifyUserOnTarget(String userId) {
        Mockito.verify(targetRestClientSpy)
                .saveUser(Mockito.argThat(i -> userId.equals(i.getId())));
        Mockito.verify(targetRestClientSpy)
                .findUserById(userId);
    }

    @Test
    void testTokenMigration() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/migration/tokens")
                        .header("Authorization", "Bearer AUTHTOKEN")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().string("OK"));

        Mockito.verify(sourceRestClientSpy)
                .findTokens(0,1);
        Mockito.verify(sourceRestClientSpy)
                .findTokens(1,1);
        Mockito.verify(sourceRestClientSpy)
                .findTokens(2,1);
        Mockito.verify(sourceRestClientSpy)
                .findTokens(3,1);

        verifyTokenOnTarget("TOKENID__0");
        verifyTokenOnTarget("TOKENID__1");
        verifyTokenOnTarget("TOKENID__2");
    }

    private void verifyTokenOnTarget(String tokenId) {
        Mockito.verify(targetRestClientSpy)
                .saveToken(Mockito.argThat(i -> tokenId.equals(i.getId())));
        Mockito.verify(targetRestClientSpy)
                .findTokenById(tokenId);
    }
}
