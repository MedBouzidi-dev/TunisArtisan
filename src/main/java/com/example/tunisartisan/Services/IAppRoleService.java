package com.example.tunisartisan.Services;

import com.example.tunisartisan.Entities.AppRole;
import java.util.List;

public interface IAppRoleService {
    AppRole saveAppRole(AppRole appRole);
    AppRole getAppRoleById(String id);
    List<AppRole> getAllAppRoles();
    AppRole updateAppRole(String id, AppRole appRole);
    void deleteAppRole(String id);
}

