package com.peterporzuczek.backend.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.peterporzuczek.backend.domain.User;
import com.peterporzuczek.backend.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@PropertySource(value = "classpath:/application.properties", ignoreResourceNotFound = true)
@RequestMapping("/${resources.intranet.profile}")
public class AccountProfileController {

    private static final Logger LOG = LoggerFactory.getLogger(AccountProfileController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value ="/current", method = RequestMethod.GET)
    public @ResponseBody
    User getCurrent(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User profile = userService.findOneByUsername(auth.getName());
        return profile;
    }

    @RequestMapping(value ="/current/update", method = RequestMethod.PUT)
    public
    boolean updateCurrent(@RequestBody Map<String, String> formParams){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User profile = userService.findOneByUsername(auth.getName());
        JsonElement element = new JsonParser().parse(formParams.get("data"));
        JsonObject elementObject = element.getAsJsonObject();

        String username = elementObject.get("username").getAsString();
        String firstName = elementObject.get("firstName").getAsString();
        String lastName = elementObject.get("lastName").getAsString();
        String password = elementObject.get("password").getAsString();
        String oldPassword = elementObject.get("oldPassword").getAsString();
        String email = elementObject.get("email").getAsString();

        if (    StringUtils.isEmpty(username) || StringUtils.isEmpty(firstName) ||
                StringUtils.isEmpty(lastName) || StringUtils.isEmpty(password) ||
                StringUtils.isEmpty(oldPassword) || StringUtils.isEmpty(email) ||
                !passwordEncoder.matches(oldPassword, profile.getPassword()) ||
                !profile.getUsername().equals(username) ) {

            return false;
        } else {
            profile.setEmail(email);
            profile.setPassword(passwordEncoder.encode(password));
            profile.setFirstName(firstName);
            profile.setLastName(lastName);
            userService.update(profile);
            return true;
        }
    }

}
