package com.daniel.userAuthFlow.dto;

import com.daniel.userAuthFlow.dto.serializer.DataResponseSerializer;
import com.daniel.userAuthFlow.dto.serializer.ShowNullWrapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonSerialize(using = DataResponseSerializer.class)
public record DataResponse(
        // for authentication
        String accessToken,
        UserResponse user,

        // api/users/:id
        String userId,
        String firstName,
        String lastName,
        String email,
        ShowNullWrapper phone,

        // "organisations": [{}]
        List<OrganisationListResponse> organisations,

        // organisation by id
        String orgId,
        String name,
        ShowNullWrapper description
) {

}
