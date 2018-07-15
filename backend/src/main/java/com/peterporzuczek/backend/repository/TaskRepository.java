package com.peterporzuczek.backend.repository;

import com.peterporzuczek.backend.domain.Role;
import com.peterporzuczek.backend.domain.Task;
import com.peterporzuczek.backend.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;


@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task, Long>, JpaSpecificationExecutor<Task>, CrudRepository<Task, Long> {

    Task findByTimeAndUsername(String time, String username);
    Task findByTitle(String title);
    List<Task> findAllByUsername(String username);
    Page<Task> findByUsername(Pageable pageable, String username);
    Page<Task> findByUsername(Specification spec, Pageable pageable, String username);
    Task findByIdentification(Long Id);
}
