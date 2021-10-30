package com.example.transport_company;

import com.example.transport_company.exception.ResourceNotFoundException;

public class RestPreconditions {

    public static <T> T checkResource(T resource) {
        if (resource == null | !checkObject(resource)) {
            throw new ResourceNotFoundException();
        } else {
            return resource;
        }
    }

    public static <T> void checkResources(T resource1, T resource2) {
        if (resource1 == null | resource2 == null) throw new ResourceNotFoundException();
        if (resource1.toString().trim().isEmpty()
                | resource2.toString().trim().isEmpty()) throw new ResourceNotFoundException();
    }

    public static <T> boolean checkObject(T object) {
        return !object.toString().trim().isEmpty();
    }
}
