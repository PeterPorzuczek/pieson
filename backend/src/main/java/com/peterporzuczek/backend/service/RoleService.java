package com.peterporzuczek.backend.service;

import com.peterporzuczek.backend.domain.Role;
import com.peterporzuczek.backend.domain.User;
import com.peterporzuczek.backend.repository.RoleRepository;
import com.peterporzuczek.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Boolean delete(Role role) {
        roleRepository.delete(role);
        return true;
    }

    public Role add(Role role) {
        roleRepository.save(role);
        return role;
    }

    public Role update(Role role) {
        roleRepository.save(role);
        return role;
    }

    public Role findOneByRoleName(String name) { return roleRepository.findByRoleName(name); }

    public Role findOneById(Long id) { return roleRepository.findById(id); }

    public List<Role> findAllRoles() { return (List<Role>)roleRepository.findAll(); }

    public Page<Role> listAllByPage(Pageable pageable) { return roleRepository.findAll(pageable); }

    public Page<Role> listAllByPageWithFilter(Specification spec, Pageable pageable) { return roleRepository.findAll(spec, pageable); }

    public Role findOneByName(String name) { return roleRepository.findByRoleName(name); }
}
