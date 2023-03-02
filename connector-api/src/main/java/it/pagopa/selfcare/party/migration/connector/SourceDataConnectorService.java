package it.pagopa.selfcare.party.migration.connector;

import it.pagopa.selfcare.party.migration.connector.model.NewDesignToken;
import it.pagopa.selfcare.party.migration.connector.model.NewDesignUser;

import java.util.List;

public interface SourceDataConnectorService {
    List<NewDesignUser> getUsers(int page, int pageSize);
    List<NewDesignInstitution> getInstitutions(int page, int pageSize);
    List<NewDesignToken> getTokens(int page, int pageSize);
}
