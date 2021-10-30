package com.example.transport_company.repository;

import com.example.transport_company.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {

    Status findStatusById(long id);

    Status findStatusByName(String name);
}
