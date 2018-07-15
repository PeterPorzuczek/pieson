package com.peterporzuczek.backend.service;

import com.peterporzuczek.backend.domain.User;
import com.peterporzuczek.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService  implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);

        if(user == null) {
            throw new UsernameNotFoundException(String.format("The username %s doesn't exist", s));
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        });

        UserDetails userDetails = new org.springframework.security.core.userdetails.
                User(user.getUsername(), user.getPassword(), authorities);

        return userDetails;
    }

    public Boolean delete(User user) {
        userRepository.delete(user);
        return true;
    }

    public User add(User user) {
        userRepository.save(user);
        return user;
    }

    public User update(User user) {
        userRepository.save(user);
        return user;
    }

    public User findOneByUsername(String name) { return userRepository.findByUsername(name); }

    public User findOneById(Long id) { return userRepository.findById(id); }

    public List<User> findAllUsers() { return (List<User>)userRepository.findAll(); }

    public Page<User> listAllByPage(Pageable pageable) { return userRepository.findAll(pageable); }

    public Page<User> listAllByPageWithFilter(Specification spec, Pageable pageable) { return userRepository.findAll(spec, pageable); }



    public void updateLastLogin(String userName) {
        this.userRepository.updateLastLogin(userName);
    }

}

