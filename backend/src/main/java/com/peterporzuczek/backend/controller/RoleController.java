package com.peterporzuczek.backend.controller;

import com.peterporzuczek.backend.domain.Role;
import com.peterporzuczek.backend.domain.User;
import com.peterporzuczek.backend.service.RoleService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@PreAuthorize("hasAuthority('ADMINISTRATOR_USER')")
@PropertySource(value = "classpath:/application.properties", ignoreResourceNotFound = true)
@RequestMapping("/${resources.intranet.role}")
public class RoleController {

    private static final Logger LOG = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @RequestMapping(value="/list/all",method=RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMINISTRATOR_USER')")
    public List<Role> list () {
        return roleService.findAllRoles();
    }

    @RequestMapping(value = "/list")
    @PreAuthorize("hasAuthority('ADMINISTRATOR_USER')")
    public Page<Role> find(@PageableDefault(sort = { "roleName", "description" }) Pageable pageable,
                           @Or({

                                   @Spec(params="filter", path="roleName", spec=Like.class),
                                   @Spec(params="filter", path="description", spec=Like.class)}) Specification spec){

        return roleService.listAllByPageWithFilter(spec, pageable);
    }

    @RequestMapping(value="/delete/{id}",method= RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('ADMINISTRATOR_USER')")
    public boolean delete (@PathVariable("id") Long id) {
        Role check = roleService.findOneById(id);
        if ( check != null ) { roleService.delete( check ); return true; } else { return false; }
    }

    @RequestMapping(value="/update",method=RequestMethod.PUT)
    @PreAuthorize("hasAuthority('ADMINISTRATOR_USER')")
    public boolean update (Role role) {
        Role check = roleService.findOneById(role.getId());
        if ( check != null ) { roleService.update( check ); return true; } else { return false; }
    }

    @RequestMapping(value="/add",method=RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMINISTRATOR_USER')")
    public boolean add (Role role) {
        Role check = roleService.findOneByRoleName(role.getRoleName());
        if ( check != null ) { roleService.add( check ); return true; } else { return false; }
    }

}
