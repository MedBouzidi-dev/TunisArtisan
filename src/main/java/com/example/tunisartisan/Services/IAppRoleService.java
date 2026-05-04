package com.example.tunisartisan.Services;

import com.example.tunisartisan.Entities.AppRole;
import java.util.List;

public interface IAppRoleService {
    AppRole saveAppRole(AppRole appRole);
    AppRole getAppRoleById(Long id);
    List<AppRole> getAllAppRoles();
    AppRole updateAppRole(Long id, AppRole appRole);
    void deleteAppRole(Long id);
}

