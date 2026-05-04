package com.example.tunisartisan.Services;

import com.example.tunisartisan.Entities.AppRole;
import com.example.tunisartisan.Repositories.AppRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppRoleService implements IAppRoleService {
    @Autowired
    private AppRoleRepository appRoleRepository;

    @Override
    public AppRole saveAppRole(AppRole appRole) {
        return appRoleRepository.save(appRole);
    }

    @Override
    public AppRole getAppRoleById(Long id) {
        Optional<AppRole> appRole = appRoleRepository.findById(id);
        return appRole.orElse(null);
    }

    @Override
    public List<AppRole> getAllAppRoles() {
        return appRoleRepository.findAll();
    }

    @Override
    public AppRole updateAppRole(Long id, AppRole appRole) {
        if (appRoleRepository.existsById(id)) {
            appRole.setId(id);
            return appRoleRepository.save(appRole);
        }
        return null;
    }

    @Override
    public void deleteAppRole(Long id) {
        appRoleRepository.deleteById(id);
    }
}

