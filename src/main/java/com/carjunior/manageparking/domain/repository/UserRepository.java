package com.carjunior.manageparking.domain.repository;

import com.carjunior.manageparking.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    boolean existsVehicleByEmailAndIdNot(String email, long id);
    @Query("UPDATE User u SET u.status = not u.status WHERE u.id = id:")
    void changeStatus(long id);
}
