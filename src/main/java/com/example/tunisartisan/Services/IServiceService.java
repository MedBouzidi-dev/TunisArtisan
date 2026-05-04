package com.example.tunisartisan.Services;

import com.example.tunisartisan.Entities.Service;
import java.util.List;

public interface IServiceService {
    Service saveService(Service service);
    Service getServiceById(Long id);
    List<Service> getAllServices();
    Service updateService(Long id, Service service);
    void deleteService(Long id);
}

