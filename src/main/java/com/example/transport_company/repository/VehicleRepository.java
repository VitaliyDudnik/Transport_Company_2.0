package com.example.transport_company.repository;

import com.example.transport_company.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Transactional(readOnly = true)
    Vehicle findVehicleById(long id);

    @Transactional(readOnly = true)
    List<Vehicle> findByStatus(String status);

    @Modifying
    @Query("update Vehicle v set v.status = :status where v.id = :id")
    void updateStatus(@Param("status") String status, @Param("id") long id);
}
