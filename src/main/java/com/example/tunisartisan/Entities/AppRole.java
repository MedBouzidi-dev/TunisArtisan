package com.example.tunisartisan.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "AppRole")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppRole {
    @Id
    private String role;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();
}

