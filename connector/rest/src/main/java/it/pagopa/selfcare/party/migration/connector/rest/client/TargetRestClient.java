package it.pagopa.selfcare.party.migration.connector.rest.client;

import it.pagopa.selfcare.party.migration.connector.generated.NewDesignInstitution;
import it.pagopa.selfcare.party.migration.connector.generated.NewDesignToken;
import it.pagopa.selfcare.party.migration.connector.generated.NewDesignUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "${rest-client.target.serviceCode}", url = "${rest-client.target.base-url}")
public interface TargetRestClient {

    @GetMapping("${rest-client.target.institutions.path}/{institutionId}")
    NewDesignInstitution findInstitutionById(@PathVariable("institutionId") String institutionId);
    @PostMapping("${rest-client.target.institutions.path}")
    NewDesignInstitution saveInstitution(NewDesignInstitution institution);

    @GetMapping("${rest-client.target.tokens.path}/{tokenId}")
    NewDesignToken findTokenById(@PathVariable("tokenId") String tokenId);
    @PostMapping("${rest-client.target.tokens.path}")
    NewDesignToken saveToken(NewDesignToken token);

    @GetMapping("${rest-client.target.users.path}/{userId}")
    NewDesignUser findUserById(@PathVariable("userId") String userId);
    @PostMapping("${rest-client.target.users.path}")
    NewDesignUser saveUser(NewDesignUser user);
}
