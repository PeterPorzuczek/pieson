package com.peterporzuczek.backend;

import com.peterporzuczek.backend.domain.Role;
import com.peterporzuczek.backend.domain.User;
import com.peterporzuczek.backend.repository.RoleRepository;
import com.peterporzuczek.backend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class InitDefaultData {

    private static final Logger LOG = LoggerFactory.getLogger(InitDefaultData.class);

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    private void initDefaultData() {
/*
        LOG.info("Init default start");

        List<Role> roles = new ArrayList<Role>();
        Role role;

        role = new Role();
        role.setId((long) 1);
        role.setRoleName("STANDARD_USER");
        role.setDescription("Standard User - Has limited rights");
        roles.add(role);

        role = new Role();
        role.setId((long) 2);
        role.setRoleName("ADMINISTRATOR_USER");
        role.setDescription("Administrator User - Has permission to perform administrator tasks");
        roles.add(role);

        role = new Role();
        role.setId((long) 3);
        role.setRoleName("BLOCKED_USER");
        role.setDescription("Blocked User - Has no rights");
        roles.add(role);

        for(Role r : roles) {
            roleRepository.save(r);
        }

        List<User> users = new ArrayList<User>();
        User user;

        user = new User();
        user.setId((long) 1);
        user.setUsername("pporzuczek");
        user.setPassword(passwordEncoder.encode("pporzuczek54321"));
        user.setConfirmPassword(passwordEncoder.encode("pporzuczek54321"));
        user.setEmail("piotr@porzuczek.pl");
        user.setFirstName("Piotr");
        user.setLastName("Porzuczek");
        user.setRoles(Arrays.asList(roleRepository.findByRoleName("ADMINISTRATOR_USER")));
        users.add(user);

        user = new User();
        user.setId((long) 2);
        user.setUsername("ojanczak");
        user.setPassword(passwordEncoder.encode("ojanczak54321"));
        user.setConfirmPassword(passwordEncoder.encode("ojanczak54321"));
        user.setEmail("oskar@janczak.pl");
        user.setFirstName("Oskar");
        user.setLastName("Janczak");
        user.setRoles(Arrays.asList(roleRepository.findByRoleName("STANDARD_USER")));
        users.add(user);

        for(User u : users) {
            userRepository.save(u);
        }

        LOG.info("Init default done");
*/
    }
}
