package com.application.service;

import com.application.entity.Role;
import com.application.entity.enumeration.UserRole;
import com.application.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(int id) {
        return roleRepository.getById(id);
    }

    public Role getRoleByName(UserRole name) {
        return roleRepository.getRoleByName(name);
    }
}
