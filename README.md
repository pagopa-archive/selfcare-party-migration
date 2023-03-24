# selfcare-party-migration
A web application having the purpose to migrate party data through REST services.

It has been implemented in order to transfer [party-management](https://github.com/pagopa/interop-be-party-management) data towards [selfcare-ms-core](https://github.com/pagopa/selfcare-ms-core).<br />
The actions performed on each entity to migrate are:

1. Call source's APIs to fetch all the data, page by page
2. For each page
   1. For each element
      1. Call the target's API to store it
      2. Call the target's API to retrieve it by id in order to check the equality with the source's element 

The design of the involved entities has been documented on [Confluence](https://pagopa.atlassian.net/wiki/spaces/SCP/pages/540246148/RFC+Riscrittura+moduli+party#Remodeling-Party-Entities-(DRAFT)) and [JIRA](https://pagopa.atlassian.net/browse/PNPG-57) 

## APIs
| Method | Path                    | Description                                            | Params |
|--------|-------------------------|--------------------------------------------------------|--------|
| GET    | /migration/institutions | The API to start the migration of Institution entities |        |
| GET    | /migration/tokens       | The API to start the migration of Token entities       |        |
| GET    | /migration/users        | The API to start the migration of User entities        |        |

## Configuration
### Core
The following environment variables allow to configure some core functionalities.

| ENV             | Description                                          | Default |
|-----------------|------------------------------------------------------|---------|
| APP_SERVER_PORT | The port at which the application server will listen | 8080    |

### Logging
The following environment variables allow to configure logging levels.

| ENV                             | Description                                                                                                                                                      | Default |
|---------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------|
| APP_LOG_LEVEL                   | The base log level                                                                                                                                               | DEBUG   |
| REST_CLIENT_LOGGER_LEVEL        | The log level defined for the REST integrations. <br />Available values are: NONE, BASIC, HEADERS, FULL                                                          | FULL    |
| SOURCE_REST_CLIENT_LOGGER_LEVEL | If defined, it will override the log level defined for the REST integrations towards Source application.<br /> See REST_CLIENT_LOGGER_LEVEL for available values | FULL    |
| TARGET_REST_CLIENT_LOGGER_LEVEL | If defined, it will override the log level defined for the REST integrations towards Target application.<br /> See REST_CLIENT_LOGGER_LEVEL for available values | FULL    |

### Business logic
The following environment variables allow to configure some business logic behavior.

| ENV                | Description                                                                   | Default |
|--------------------|-------------------------------------------------------------------------------|---------|
| STOP_ON_ERROR      | If the current migration process should exit at the first failed equals check | true    |
| SOURCE_PAGE_SIZE   | The size of the page read from the source                                     | 1000    |
| TARGET_PARALLELISM | The number of thread allocated to invoke target's APIs                        | 1       |

### Integration
The following environment variables allow to configure integrations towards external systems.

| ENV                         | Description                                                           | Default   |
|-----------------------------|-----------------------------------------------------------------------|-----------|
| REST_CLIENT_CONNECT_TIMEOUT | The default connect milliseconds timeout applied to REST integrations | 5000      |
| REST_CLIENT_READ_TIMEOUT    | The default read milliseconds timeout applied to REST integrations    | 5000      |

#### Source
The following environment variables allow to configure integrations toward the source of the data.

| ENV                                | Description                                                                                      | Default                                              |
|------------------------------------|--------------------------------------------------------------------------------------------------|------------------------------------------------------|
| SOURCE_PROTOCOL                    | The default protocol used to build the Source's APIs                                             | http                                                 |
| SOURCE_HOST                        | The default host used to build the Source's APIs                                                 | localhost                                            |
| SOURCE_PORT                        | The default port used to build the Source's APIs                                                 | 8088                                                 |
| SOURCE_BASE_URL                    | The base URL used as prefix for all the Source's API.<br/>If defined it will ignore the previous | http://localhost:8088/party-management/0.0/newdesign |
| SOURCE_PATH_INSTITUTIONS           | The path, concatenated to the base url, to invoke the API to fetch the Institution documents     | /institutions                                        |
| SOURCE_PATH_TOKENS                 | The path, concatenated to the base url, to invoke the API to fetch the Token documents           | /tokens                                              |
| SOURCE_PATH_USERS                  | The path, concatenated to the base url, to invoke the API to fetch the User documents            | /users                                               |
| SOURCE_REST_CLIENT_CONNECT_TIMEOUT | The connect milliseconds timeout applied to Source's API                                         | 5000                                                 |
| SOURCE_REST_CLIENT_READ_TIMEOUT    | The read milliseconds timeout applied to Source's API                                            | 5000                                                 |

#### Target
The following environment variables allow to configure integrations toward the target of the data.

| ENV                                | Description                                                                                                  | Default                         |
|------------------------------------|--------------------------------------------------------------------------------------------------------------|---------------------------------|
| TARGET_PROTOCOL                    | The default protocol used to build the Target's APIs                                                         | http                            |
| TARGET_HOST                        | The default host used to build the Target's APIs                                                             | localhost                       |
| TARGET_PORT                        | The default port used to build the Target's APIs                                                             | 8080                            |
| TARGET_BASE_URL                    | The base URL used as prefix for all the Target's API.<br/>If defined it will ignore the previous             | http://localhost:8080/migration |
| TARGET_PATH_INSTITUTIONS           | The path, concatenated to the base url, to invoke the API to store and fetch by id the Institution documents | /institution                    |
| TARGET_PATH_TOKENS                 | The path, concatenated to the base url, to invoke the API to store and fetch by id the Token documents       | /token                          |
| TARGET_PATH_USERS                  | The path, concatenated to the base url, to invoke the API to store and fetch by id the User documents        | /user                           |
| TARGET_REST_CLIENT_CONNECT_TIMEOUT | The connect milliseconds timeout applied to Target's API                                                     | 5000                            |
| TARGET_REST_CLIENT_READ_TIMEOUT    | The read milliseconds timeout applied to Target's API                                                        | 5000                            |

## Throubleshouting

Due to the use of selfcare-commons security config, it will configure the authentication through k8s. 
If you defined the KUBECONFIG env var, or the default kubectl config file exists, you will be asked to allow the authentication.
If the config file doesn't exists, it will ignore it and the application will be able to start successfully.

If starting the application you see the following exception:
```
com.microsoft.aad.adal4j.AdalClaimsChallengeException: {"error":"interaction_required"}
```
You have to authenticate your kubectl client, or remove the used config file. 