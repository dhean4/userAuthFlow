package com.daniel.userAuthFlow.repository;

import com.daniel.userAuthFlow.entity.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganisationRepository extends JpaRepository<Organisation, String> {
}