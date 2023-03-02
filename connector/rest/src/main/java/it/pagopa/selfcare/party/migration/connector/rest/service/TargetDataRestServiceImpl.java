package it.pagopa.selfcare.party.migration.connector.rest.service;

import it.pagopa.selfcare.party.migration.connector.TargetDataConnectorService;
import it.pagopa.selfcare.party.migration.connector.generated.NewDesignInstitution;
import it.pagopa.selfcare.party.migration.connector.generated.NewDesignToken;
import it.pagopa.selfcare.party.migration.connector.generated.NewDesignUser;
import it.pagopa.selfcare.party.migration.connector.rest.client.TargetRestClient;
import org.springframework.stereotype.Service;

@Service
public class TargetDataRestServiceImpl implements TargetDataConnectorService {

    private final TargetRestClient targetRestClient;

    public TargetDataRestServiceImpl(TargetRestClient targetRestClient) {
        this.targetRestClient = targetRestClient;
    }

    @Override
    public NewDesignUser saveUser(NewDesignUser user) {

        return targetRestClient.saveUser(user);
    }

    @Override
    public NewDesignUser findUserById(String id) {
        return targetRestClient.findUserById(id);
    }

    @Override
    public NewDesignInstitution saveInstitution(NewDesignInstitution institution) {
        return targetRestClient.saveInstitution(institution);
    }

    @Override
    public NewDesignInstitution findInstitutionById(String id) {
        return targetRestClient.findInstitutionById(id);
    }

    @Override
    public NewDesignToken saveToken(NewDesignToken token) {
        return targetRestClient.saveToken(token);
    }

    @Override
    public NewDesignToken findTokenById(String id) {
        return targetRestClient.findTokenById(id);
    }
}
