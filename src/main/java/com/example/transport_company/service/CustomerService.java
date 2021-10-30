package com.example.transport_company.service;

import com.example.transport_company.RestPreconditions;
import com.example.transport_company.entity.Customer;
import com.example.transport_company.entity.Order;
import com.example.transport_company.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderService orderService;

    @CachePut(value = "customers")
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    public void addOrder(String orderEmail, long customerId) {
        RestPreconditions.checkResource(orderEmail.trim());
        Set<Order> order = orderService.getAllByEmail(orderEmail);
        Customer customer = getById(customerId);
        customer.setOrders(order);
        save(customer);
    }

    @Cacheable(value = "customers", key = "#id")
    public Customer getById(long id) {
        return RestPreconditions.checkResource(customerRepository.findById(id));
    }

    @Cacheable(value = "customers")
    public List<Customer> findAll() {
        return RestPreconditions.checkResource(customerRepository.findAll());
    }

    public Customer findByEmail(String email) {
        return RestPreconditions.checkResource(customerRepository.findByEmail(email));
    }

    @CacheEvict(value = "customers", allEntries = true)
    public void delete(long id) {
        getById(id);
        customerRepository.deleteById(id);
    }
}
