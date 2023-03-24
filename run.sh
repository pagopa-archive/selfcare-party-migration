export APP_LOG_LEVEL=INFO
# party-management pod ip
export SOURCE_HOST=localhost
export SOURCE_PORT=8088
# ms-core pod ip
export TARGET_HOST=localhost
export TARGET_PORT=8080

############### DEV
#WELL_KNOWN_URL=https://selcdcheckoutsa.z6.web.core.windows.net/.well-known/jwks.json
export JWT_TOKEN_PUBLIC_KEY="-----BEGIN PUBLIC KEY-----
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqPL8GatL2VO4ML+X0bSe
C5qojskhbJDDbwnp7wlhrIjkXIeb2tKhPng3GgYIAuntDImxXI7Q0l6Zs3/3cX8W
ZGJi7usJdyUTNjSXir7PAZA3/hwboIkujjfOObaEFbaljy74zQA/9io2B+vugnJ/
YwQKq4scElWEh83ng6Z+Ec8I+QXI/3Pm5DZNcihWE5dCxFcugAorNeg8VriaeE3V
6cOzF2vY/RYa2hHQQCzXhJE9P1FCIVZM3w82i+cTjMhCTu65FFjC6GhOSSlCtCmc
5eVXtHPrpYwgGoknu713Om47P9dvk1Dv4CDktV7DQnDJZ9UY94yWMOfg13KSd3+X
mwIDAQAB
-----END PUBLIC KEY-----"

############### UAT
#WELL_KNOWN_URL=https://selcucheckoutsa.z6.web.core.windows.net/.well-known/jwks.json
#export JWT_TOKEN_PUBLIC_KEY="-----BEGIN PUBLIC KEY-----
#MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwsAv72AMOHembj6DyJjq
#EfIDNCLcV6spAbaJD/z1DsIcdBRiZO2KzsEO9oazeCN1Dk4wEKUTbWUvP/FjqI9w
#mAYQaGJuYr0pBbgYnec6Kbw+5Y2dM0S5+X7d4WACIQlnqSIDJEKnGwB9248cIF1d
#y4K4AeiDfZp1SkGSmsMe6um5r3jvz28/YGPOciJGSbhw7EniJaP3UofARGtqCjSo
#/VWhFzXpFnQOqge8+Ie5KyRW5cvglKvvsYl/AVGUrk/kZv+/nPHSYzsCZnfDM/2m
#R36H65FEaLkyB8Upa8DFa75OJxNvZWRL+znz17AYm0rgEjnO1cb79zOSdFd+OPCF
#lQIDAQAB
#-----END PUBLIC KEY-----"



############### PROD
#WELL_KNOWN_URL=https://selcpcheckoutsa.z6.web.core.windows.net/.well-known/jwks.json
#export JWT_TOKEN_PUBLIC_KEY="-----BEGIN PUBLIC KEY-----
#MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2d1lkBgZlrZLlPgTQCDo
#v9S5oE+DFXYx/+GtRwhnLzYFnR1ZxR2gIHztdvcFW9w+RUlve5A4KOYHbs9tuWiw
#zmMqyS/U5fiR5f8oNejwO5K/pDWIDz3uyqmQnxeKM2iDIflK6Vyz1pGnJN3mxxZn
#8gh1dTi4Z87qAVdewpbVNB1RWDlpqIlQZ5LZaFsDBEhSO55IX14q6wdIo1OVwbQK
#tDK55tO8W0/eO66i+RH3d6/BopHDN+L7CPHZzjADwE3SWTbS3hgIlVR2pJXoCvZ7
#V+AKeAVe0ZIgp+Sy+YRCSnGeD9fGkX7s8DUeCDLuAZtp3UIv9SA9QOtn5vKj+1p6
#4QIDAQAB
#-----END PUBLIC KEY-----"


java -jar ./target/selc-party-migration-1.0-SNAPSHOT-FATJAR.jar