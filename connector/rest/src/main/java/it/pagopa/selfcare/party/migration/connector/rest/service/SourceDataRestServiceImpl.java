package it.pagopa.selfcare.party.migration.connector.rest.service;

import it.pagopa.selfcare.party.migration.connector.SourceDataConnectorService;
import it.pagopa.selfcare.party.migration.connector.generated.NewDesignInstitution;
import it.pagopa.selfcare.party.migration.connector.generated.NewDesignToken;
import it.pagopa.selfcare.party.migration.connector.generated.NewDesignUser;
import it.pagopa.selfcare.party.migration.connector.rest.client.SourceRestClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SourceDataRestServiceImpl implements SourceDataConnectorService {

    private final SourceRestClient sourceRestClient;

    public SourceDataRestServiceImpl(SourceRestClient sourceRestClient) {
        this.sourceRestClient = sourceRestClient;
    }

    @Override
    public List<NewDesignUser> getUsers(int page, int pageSize) {
        return sourceRestClient.findNewDesignUsers(page, pageSize);
    }

    @Override
    public List<NewDesignInstitution> getInstitutions(int page, int pageSize) {
        return sourceRestClient.findNewDesignInstitutions(page, pageSize);
    }

    @Override
    public List<NewDesignToken> getTokens(int page, int pageSize) {
        return sourceRestClient.findNewDesignTokens(page, pageSize);
    }
}
