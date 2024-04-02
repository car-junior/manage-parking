package com.carjunior.manageparking.domain.repository;

import com.carjunior.manageparking.domain.entity.User;
import com.carjunior.manageparking.domain.entity.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    boolean existsVehicleByEmailAndIdNot(String email, long id);
    @Query("UPDATE User u SET u.status = :status WHERE u.id = :id")
    void changeStatus(@Param("id") long id, @Param("status") UserStatus status);

    boolean existsUserById(long userId);
}
