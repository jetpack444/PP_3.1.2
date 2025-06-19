package ru.kata.spring.boot_security.demo.services;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

@Service
public interface RoleService {
    List<Role> getAllRoles();

    Role getRole(String userRole);

    Role getRoleById(int id);

    void addRole(Role role);
}
