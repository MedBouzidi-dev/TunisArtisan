package com.example.tunisartisan.Services;

import com.example.tunisartisan.Entities.Service;
import com.example.tunisartisan.Repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceService implements IServiceService {
    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public Service saveService(Service service) {
        return serviceRepository.save(service);
    }

    @Override
    public Service getServiceById(Long id) {
        Optional<Service> service = serviceRepository.findById(id);
        return service.orElse(null);
    }

    @Override
    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    @Override
    public Service updateService(Long id, Service service) {
        if (serviceRepository.existsById(id)) {
            service.setIdservice(id);
            return serviceRepository.save(service);
        }
        return null;
    }

    @Override
    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }
}

