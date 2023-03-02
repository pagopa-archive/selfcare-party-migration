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
    public void saveUser(NewDesignUser user) {
        //TODO
    }

    @Override
    public NewDesignUser findUserById(String id) {
        return null;//TODO
    }

    @Override
    public void saveInstitution(NewDesignInstitution institution) {
//TODO
    }

    @Override
    public NewDesignInstitution findInstitutionById(String id) {
        return null;//TODO
    }

    @Override
    public void saveToken(NewDesignToken token) {
//TODO
    }

    @Override
    public NewDesignToken findTokenById(String id) {
        return null;//TODO
    }
}
