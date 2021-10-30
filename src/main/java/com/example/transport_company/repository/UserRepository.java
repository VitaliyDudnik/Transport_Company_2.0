package com.example.transport_company.repository;

import com.example.transport_company.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Query("update User u set u.email = :email where u.id = :id")
    void updateUserEmail(@Param("id") long id, @Param("email") String email);

    @Modifying
    @Query("update User u set u.password = :password where u.id = :id")
    void updateUserPassword(@Param("id") long id, @Param("password") String password);

    @Transactional(readOnly = true)
    User getUserByEmail(String email);

    @Transactional(readOnly = true)
    User getUserById(long id);

}
