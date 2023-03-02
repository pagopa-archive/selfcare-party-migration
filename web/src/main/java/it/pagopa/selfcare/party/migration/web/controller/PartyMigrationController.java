package it.pagopa.selfcare.party.migration.web.controller;

import io.swagger.annotations.Api;
import it.pagopa.selfcare.party.migration.core.MigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/migration")
@Api(tags = "migration")
public class PartyMigrationController {

    private final MigrationService migrationService;


    @Autowired
    public PartyMigrationController(MigrationService migrationService) {
        this.migrationService = migrationService;
    }

    @GetMapping("/users")
    public String migrateUsers(){
        return migrationService.migrateUsers()? "OK" : "KO";
    }

    @GetMapping("/institutions")
    public String migrateInstitutions(){
        return migrationService.migrateInstitutions()? "OK" : "KO";
    }

    @GetMapping("/tokens")
    public String migrateTokens(){
        return migrationService.migrateTokens()? "OK" : "KO";
    }

}
