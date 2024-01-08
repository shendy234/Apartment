package com.enigma.apartment.service;

import com.enigma.apartment.entity.Role;

public interface RoleService {
    Role getOrSave(Role role);
}
