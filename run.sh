# party-management pod ip
SOURCE_HOST=localhost
# ms-core pod ip
TARGET_HOST=localhost

./mvnw package -DskipTests && java -jar ./target/selc-party-migration-0.0.1-SNAPSHOT-FATJAR.jar