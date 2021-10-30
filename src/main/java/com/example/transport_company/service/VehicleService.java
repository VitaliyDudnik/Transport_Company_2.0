package com.example.transport_company.service;

import com.example.transport_company.RestPreconditions;
import com.example.transport_company.entity.Status;
import com.example.transport_company.entity.Vehicle;
import com.example.transport_company.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private StatusService statusService;

    public void save(Vehicle vehicle) {
        RestPreconditions.checkResource(vehicle);
        Status status = statusService.findById(7);
        vehicle.setStatus(status.getName());
        vehicleRepository.save(vehicle);
    }

    public Vehicle findById(long id) {
        return RestPreconditions.checkResource(vehicleRepository.findVehicleById(id));
    }

    public List<Vehicle> findAll() {
        return RestPreconditions.checkResource(vehicleRepository.findAll());
    }

    public List<Vehicle> findByStatusId(long id) {
        Status status = RestPreconditions.checkResource(statusService.findById(id));
        return vehicleRepository.findByStatus(status.getName());
    }

    public void updateStatus(long id, String statusName) {
        findById(id);
        RestPreconditions.checkResource(statusName);
        vehicleRepository.updateStatus(statusName, id);
    }

    public void delete(long id) {
        findById(id);
        vehicleRepository.deleteById(id);
    }
}
