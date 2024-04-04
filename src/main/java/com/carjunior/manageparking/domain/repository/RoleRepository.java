package com.carjunior.manageparking.domain.repository;

import com.carjunior.manageparking.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {
    boolean existsRoleByNameAndIdNot(String name, long id);
}
