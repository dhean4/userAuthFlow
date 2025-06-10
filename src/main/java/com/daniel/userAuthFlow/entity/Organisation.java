package com.daniel.userAuthFlow.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "organisations")
public class Organisation {
    @Id
    private String orgId;

    @NotBlank(message = "Organisation name is required")
    private String orgName;

    private String description;

    @ManyToMany
    @JoinTable(
            name = "organisation_users",
            joinColumns = @JoinColumn(name = "org_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users = new HashSet<>();

    public Organisation() {
        this.orgId = UUID.randomUUID().toString();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
