package com.daniel.userAuthFlow.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateOrganisationRequest (
    String name,
    String description
) {}
