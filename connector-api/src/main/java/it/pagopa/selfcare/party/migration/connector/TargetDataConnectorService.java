package it.pagopa.selfcare.party.migration.connector;

import it.pagopa.selfcare.party.migration.connector.model.NewDesignToken;
import it.pagopa.selfcare.party.migration.connector.model.NewDesignUser;

public interface TargetDataConnectorService {
    void saveUser(NewDesignUser user);
    NewDesignUser findUserById(String id);

    void saveInstitution(NewDesignInstitution institution);
    NewDesignInstitution findInstitutionById(String id);

    void saveToken(NewDesignToken token);
    NewDesignToken findTokenById(String id);
}
