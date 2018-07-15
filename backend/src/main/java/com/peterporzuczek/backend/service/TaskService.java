package com.peterporzuczek.backend.service;

import com.peterporzuczek.backend.domain.Task;
import com.peterporzuczek.backend.domain.User;
import com.peterporzuczek.backend.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Boolean delete(Task task) {
        taskRepository.delete(task);
        return true;
    }

    public Task add(Task task) {
        taskRepository.save(task);
        return task;
    }

    public Task update(Task task) {
        taskRepository.save(task);
        return task;
    }

    public Task findOneByTitle(String title) { return taskRepository.findByTitle(title); }

    public Task findOneByIdentification(Long id) { return taskRepository.findByIdentification(id); }

    public Task findOneByIdentificationAndUsername(String time, String username) { return taskRepository.findByTimeAndUsername(time, username); }

    public List<Task> findAllTasks() { return (List<Task>)taskRepository.findAll(); }

    public List<Task> findAllTasksCount() { return (List<Task>)taskRepository.findAll(); }

    public List<Task> findAllByUsername(String username) { return (List<Task>)taskRepository.findAllByUsername(username); }

    public Page<Task> listAllByPage(Pageable pageable) { return taskRepository.findAll(pageable); }

    public Page<Task> listAllByPageForUsername(Pageable pageable, String username) { return taskRepository.findByUsername(pageable, username); }

    public Page<Task> listAllByPageWithFilter(Specification spec, Pageable pageable) { return taskRepository.findAll(spec, pageable); }

    public Page<Task> listAllByPageWithFilterForUser(Specification spec, Pageable pageable, String username) { return taskRepository.findByUsername(spec, pageable, username); }
}
