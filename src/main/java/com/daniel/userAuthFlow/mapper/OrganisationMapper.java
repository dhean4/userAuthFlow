package com.daniel.userAuthFlow.mapper;

import com.daniel.userAuthFlow.dto.OrganisationListResponse;
import com.daniel.userAuthFlow.entity.Organisation;
import org.springframework.stereotype.Service;

@Service
public class OrganisationMapper {
    public OrganisationListResponse mapToOrganisationListResponse(
            Organisation organisation
    ) {
        return OrganisationListResponse.builder()
                .orgId(organisation.getOrgId())
                .name(organisation.getName())
                .description(organisation.getDescription())
                .build();
    }
}
