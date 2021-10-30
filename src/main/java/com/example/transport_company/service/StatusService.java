package com.example.transport_company.service;

import com.example.transport_company.RestPreconditions;
import com.example.transport_company.entity.Status;
import com.example.transport_company.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    public void save(Status status) {
        RestPreconditions.checkResource(status);
        statusRepository.save(status);
    }

    public Status findStatusByName(String name) {
        return statusRepository.findStatusByName(name);
    }

    public List<Status> findAll() {
        return RestPreconditions.checkResource(statusRepository.findAll());
    }

    public Status findById(long id) {
        return RestPreconditions.checkResource(statusRepository.findStatusById(id));
    }

    public void delete(long id) {
        RestPreconditions.checkResource(statusRepository.findStatusById(id));
        statusRepository.deleteById(id);
    }
}
