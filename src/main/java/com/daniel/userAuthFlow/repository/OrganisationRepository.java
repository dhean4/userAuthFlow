package com.daniel.userAuthFlow.repository;

import com.daniel.userAuthFlow.entity.Organisation;
import com.daniel.userAuthFlow.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganisationRepository extends JpaRepository<Organisation, String> {
    List<Organisation> findByCreator(User creator);
//    Optional<Organisation> save(Organisation organisation);
}
