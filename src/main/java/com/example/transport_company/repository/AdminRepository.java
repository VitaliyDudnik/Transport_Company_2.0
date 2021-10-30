package com.example.transport_company.repository;

import com.example.transport_company.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AdminRepository extends JpaRepository<User, Long> {

    @Modifying
    @Query("update User u set u.status.id = :statusId where u.id = :userId ")
    void updateUserStatus(@Param("userId") long id, @Param("statusId") long statusId);

    @Modifying
    @Query("update User u set u.roles.id = :roleId where u.id = :userId")
    void updateUserRole(@Param("userId") long userId, @Param("roleId") long roleId);
}
