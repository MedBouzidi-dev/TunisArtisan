package com.example.tunisartisan.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.HashSet;

@Entity
@Table(name = "AppRole")
public class AppRole {
    @Id
    private String role;

    @ManyToMany(mappedBy = "roles")
    private Set<AppUser> users = new HashSet<>();
}

