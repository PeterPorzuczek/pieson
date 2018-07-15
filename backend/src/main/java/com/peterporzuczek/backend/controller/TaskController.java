package com.peterporzuczek.backend.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.peterporzuczek.backend.domain.Task;
import com.peterporzuczek.backend.domain.User;
import com.peterporzuczek.backend.service.RoleService;
import com.peterporzuczek.backend.service.TaskService;
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
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@RestController
@PropertySource(value = "classpath:/application.properties", ignoreResourceNotFound = true)
@RequestMapping("/${resources.intranet.task}")
public class TaskController {
    private static final Logger LOG = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @PreAuthorize("hasAuthority('ADMINISTRATOR_USER')")
    @RequestMapping(value = "/list/all")
    public Page<Task> findAll(@PageableDefault(sort = { "title", "description" }) Pageable pageable,
                           @Or({
                                   @Spec(params="filter", path="title", spec=Like.class),
                                   @Spec(params="filter", path="description", spec=Like.class)}) Specification spec){

        return taskService.listAllByPageWithFilter(spec, pageable);
    }

    @PreAuthorize("hasAuthority('ADMINISTRATOR_USER')")
    @RequestMapping(value = "/list/all/count", method= RequestMethod.GET)
    @ResponseBody
    public int findAllCount(){
        return taskService.findAllTasks().size();
    }

    @RequestMapping(value = "/list/count", method= RequestMethod.GET)
    @ResponseBody
    public int findAllByUsernameCount(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findOneByUsername(auth.getName());
        return taskService.findAllByUsername(user.getUsername()).size();
    }

//    @RequestMapping(value = "/list")
//    public Page<Task> find(@PageableDefault(sort = { "title", "description" }) Pageable pageable,
//                           @Or({
//                                   @Spec(params="filter", path="title", spec=Like.class),
//                                   @Spec(params="filter", path="description", spec=Like.class)}) Specification spec){
//
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findOneByUsername(auth.getName());
//        return taskService.listAllByPageWithFilterForUser(spec, pageable, user.getUsername());
//    }

    @RequestMapping(value = "/list")
    public Page<Task> find(@PageableDefault(sort = { "title", "description" }) Pageable pageable){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findOneByUsername(auth.getName());
        return taskService.listAllByPageForUsername(pageable, user.getUsername());
    }

    @RequestMapping(value="/delete/{time}/{username}", method= RequestMethod.DELETE)
    public boolean delete (@PathVariable("time") String time, @PathVariable("username") String username) {
        Task check = taskService.findOneByIdentificationAndUsername(time, username);
        if ( check != null ) { taskService.delete( check ); return true; } else { return false; }
    }

    @RequestMapping(value="/update", method=RequestMethod.PUT)
    @PreAuthorize("hasAuthority('ADMINISTRATOR_USER')")
    public boolean update (Task task) {
        Task check = taskService.findOneByIdentification(task.getIdentification());
        if ( check != null ) { taskService.update( check ); return true; } else { return false; }
    }

    @RequestMapping(value="/add",method=RequestMethod.POST)
    public
    boolean add(@RequestBody Map<String, String> formParams){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findOneByUsername(auth.getName());

        JsonElement element = new JsonParser().parse(formParams.get("data"));
        JsonObject elementObject = element.getAsJsonObject();

        Task task = new Task();
        task.setTitle(elementObject.get("title").getAsString());
        task.setDescription(elementObject.get("description").getAsString());
        task.setUsername(user.getUsername());
        task.setTime(String.valueOf(new Timestamp(System.currentTimeMillis()).getTime()));
        taskService.add(task);
        return true;
    }
}

