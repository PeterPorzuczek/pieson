package com.peterporzuczek.backend.repository;

import com.peterporzuczek.backend.domain.Role;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Long>, JpaSpecificationExecutor<Role>, CrudRepository<Role, Long> {

    Role findByRoleName(String roleName);
    Role findById(Long Id);
}
