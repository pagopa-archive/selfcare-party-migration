package it.pagopa.selfcare.party.migration.connector.rest.client;

import it.pagopa.selfcare.party.migration.connector.generated.NewDesignInstitution;
import it.pagopa.selfcare.party.migration.connector.generated.NewDesignToken;
import it.pagopa.selfcare.party.migration.connector.generated.NewDesignUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "${rest-client.source.serviceCode}", url = "${rest-client.source.base-url}")
public interface SourceRestClient {

    @GetMapping("/institutions")
    List<NewDesignInstitution> findNewDesignInstitutions(@RequestParam("page") Integer page, @RequestParam("size") Integer size);

    @GetMapping("/tokens")
    List<NewDesignToken> findNewDesignTokens(@RequestParam("page") Integer page, @RequestParam("size") Integer size);

    @GetMapping("/users")
    List<NewDesignUser> findNewDesignUsers(@RequestParam("page") Integer page, @RequestParam("size") Integer size);
}
