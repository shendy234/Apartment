package com.enigma.apartment.service.impl;

import com.enigma.apartment.entity.Role;
import com.enigma.apartment.repository.RoleRepository;
import com.enigma.apartment.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl  implements RoleService {

    private final RoleRepository repository;

    @Override
    public Role getOrSave(Role role) {
        Optional<Role> optionalRole = repository.findByName(role.getName());
        if (!optionalRole.isEmpty()){
            return optionalRole.get();
        }
        return repository.save(role);
    }
}