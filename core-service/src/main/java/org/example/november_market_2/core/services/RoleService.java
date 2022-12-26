package org.example.november_market_2.core.services;

import lombok.RequiredArgsConstructor;
import org.example.november_market_2.core.entities.Role;
import org.example.november_market_2.core.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }
}
