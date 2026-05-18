package com.example.tunisartisan.Repositories;

import com.example.tunisartisan.Entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service,Long> {
    public Service findByIdservice(Long id);
}
