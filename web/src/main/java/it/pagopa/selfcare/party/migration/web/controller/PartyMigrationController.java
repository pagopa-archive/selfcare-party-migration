package it.pagopa.selfcare.party.migration.web.controller;

import io.swagger.annotations.Api;
import it.pagopa.selfcare.party.migration.core.MigrationService;
import it.pagopa.selfcare.party.migration.core.model.MigrationReport;
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
    public MigrationReport migrateUsers(){
        return migrationService.migrateUsers();
    }

    @GetMapping("/institutions")
    public MigrationReport migrateInstitutions(){
        return migrationService.migrateInstitutions();
    }

    @GetMapping("/tokens")
    public MigrationReport migrateTokens(){
        return migrationService.migrateTokens();
    }

}
