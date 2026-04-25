package com.example.tunisartisan.Repositories;

import com.example.tunisartisan.Entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, String> {
    AppRole findByRole(String role);

}
