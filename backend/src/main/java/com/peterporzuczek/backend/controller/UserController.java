package com.peterporzuczek.backend.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.peterporzuczek.backend.domain.User;
import com.peterporzuczek.backend.service.RoleService;
import com.peterporzuczek.backend.service.UserService;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@PreAuthorize("hasAuthority('ADMINISTRATOR_USER')")
@PropertySource(value = "classpath:/application.properties", ignoreResourceNotFound = true)
@RequestMapping("/${resources.intranet.user}")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/list")
    @PreAuthorize("hasAuthority('ADMINISTRATOR_USER')")
    public Page<User> find(@PageableDefault(sort = { "username", "email", "firstName", "lastName", "lastLogin" }) Pageable pageable,
                           @Or({
                                   @Spec(params="filter", path="username", spec=Like.class),
                                   @Spec(params="filter", path="email", spec=Like.class),
                                   @Spec(params="filter", path="firstName", spec=Like.class),
                                   @Spec(params="filter", path="lastName", spec=Like.class)}) Specification spec){

        return userService.listAllByPageWithFilter(spec, pageable);
    }

    @RequestMapping(value="/delete/{username}",method=RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('ADMINISTRATOR_USER')")
    public boolean delete (@PathVariable("username") String username) {
        User check = userService.findOneByUsername(username);
        if ( check != null ) { userService.delete( check ); return true; } else { return false; }
    }

    @RequestMapping(value="/update",method=RequestMethod.PUT)
    @PreAuthorize("hasAuthority('ADMINISTRATOR_USER')")
    public boolean update (User user) {
        User check = userService.findOneById(user.getId());
        if ( check != null ) { userService.update( check ); return true; } else { return false; }
    }

//    @RequestMapping(value="/add",method=RequestMethod.POST)
//    @PreAuthorize("hasAuthority('ADMINISTRATOR_USER')")
//    public boolean add (User user) {
//        User check = userService.findOneByUsername(user.getUsername());
//        if ( check == null ) { userService.add( check ); return true; } else { return false; }
//    }

    @RequestMapping(value="/add",method=RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMINISTRATOR_USER')")
    public
    boolean updateCurrent(@RequestBody Map<String, String> formParams){

        JsonElement element = new JsonParser().parse(formParams.get("data"));
        JsonObject elementObject = element.getAsJsonObject();

        User checkUser = userService.findOneByUsername(elementObject.get("username").getAsString());
        boolean checkPassword = elementObject.get("password").getAsString().equals(elementObject.get("confirmPassword").getAsString());
        if ( checkUser != null || !checkPassword ) {

            return false;
        } else {
            User user = new User();
            user.setUsername(elementObject.get("username").getAsString());
            user.setPassword(passwordEncoder.encode(elementObject.get("password").getAsString()));
            user.setConfirmPassword(passwordEncoder.encode(elementObject.get("confirmPassword").getAsString()));
            user.setEmail(elementObject.get("email").getAsString());
            user.setFirstName(elementObject.get("firstName").getAsString());
            user.setLastName(elementObject.get("lastName").getAsString());
            user.setRoles(Arrays.asList(roleService.findOneByRoleName(elementObject.get("role").getAsJsonObject().get("roleName").getAsString())));
            LOG.info(user.toString());
            userService.add(user);
            return true;
        }
    }
}

