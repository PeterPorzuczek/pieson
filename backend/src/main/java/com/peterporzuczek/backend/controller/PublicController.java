package com.peterporzuczek.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PropertySource(value = "classpath:/application.properties", ignoreResourceNotFound = true)
@RequestMapping("/${resources.public}")
public class PublicController {

    private static final Logger LOG = LoggerFactory.getLogger(PublicController.class);

    public static final String TEXT = "Public resource works as you see";
    @PreAuthorize("hasAuthority('ADMINISTRATOR_USER') or hasAuthority('STANDARD_USER')")
    @RequestMapping("/check")
    public @ResponseBody
    String check() {
        LOG.info("GET called on /check resource");
        return TEXT;
    }
}
