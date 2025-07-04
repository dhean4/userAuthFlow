package com.daniel.userAuthFlow.controller;

import com.daniel.userAuthFlow.dto.AddUserToOrganisationRequest;
import com.daniel.userAuthFlow.dto.AppResponse;
import com.daniel.userAuthFlow.dto.CreateOrganisationRequest;
import com.daniel.userAuthFlow.service.OrganisationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/organisations")
public class OrganisationController {
    private final OrganisationService organisationService;

    @GetMapping
    public ResponseEntity<AppResponse> getAllowedOrganisations(
            Authentication activeUser
    ) {
        return ResponseEntity.ok(organisationService.getAllowedOrganisation(activeUser));
    }

    @GetMapping("/{orgId}")
    public ResponseEntity<AppResponse> getAllowedOrganisationById(
            @PathVariable(name = "orgId") String orgId,
            Authentication activeUser
    ) {
        return ResponseEntity.ok(organisationService.getAllowedOrganisationById(
                orgId,
                activeUser
        ));
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<AppResponse> createOrganisation(
            @RequestBody CreateOrganisationRequest orgRequest,
            Authentication activeUser
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                organisationService.create(orgRequest, activeUser)
        );
    }

    @PostMapping(value = "/{orgId}/users", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<AppResponse> addUserToOrganisation(
            @PathVariable(name = "orgId") String orgId,
            @RequestBody AddUserToOrganisationRequest request,
            Authentication activeUser
    ) {
        return ResponseEntity.ok(organisationService.addToOrganisation(
                orgId,
                request,
                activeUser
        ));
    }
}
