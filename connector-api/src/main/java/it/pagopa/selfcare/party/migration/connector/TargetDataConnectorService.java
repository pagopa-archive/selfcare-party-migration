package it.pagopa.selfcare.party.migration.connector;

import it.pagopa.selfcare.party.migration.connector.generated.NewDesignInstitution;
import it.pagopa.selfcare.party.migration.connector.generated.NewDesignToken;
import it.pagopa.selfcare.party.migration.connector.generated.NewDesignUser;

public interface TargetDataConnectorService {
    NewDesignUser saveUser(NewDesignUser user);
    NewDesignUser findUserById(String id);

    NewDesignInstitution saveInstitution(NewDesignInstitution institution);
    NewDesignInstitution findInstitutionById(String id);

    NewDesignToken saveToken(NewDesignToken token);
    NewDesignToken findTokenById(String id);
}
