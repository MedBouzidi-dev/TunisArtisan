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
	public AppRole getAppRoleById(String id) {
		Optional<AppRole> appRole = appRoleRepository.findById(id);
		return appRole.orElse(null);
	}

    @Override
    public List<AppRole> getAllAppRoles() {
        return appRoleRepository.findAll();
    }

    @Override
	public AppRole updateAppRole(String id, AppRole appRole) {
		if (appRoleRepository.existsById(id)) {
			// appRole.setId(id); // À adapter selon la structure de AppRole
			return appRoleRepository.save(appRole);
		}
		return null;
	}

    @Override
    public void deleteAppRole(String id) {
        appRoleRepository.deleteById(id);
    }
}

