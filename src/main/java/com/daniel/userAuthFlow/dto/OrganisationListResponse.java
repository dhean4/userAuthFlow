package com.daniel.userAuthFlow.dto;

import lombok.Builder;

@Builder
public record OrganisationListResponse(
        String orgId,
        String name,
        String description
) {
}
