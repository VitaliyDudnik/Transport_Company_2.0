package com.example.transport_company.service;

import com.example.transport_company.RestPreconditions;
import com.example.transport_company.entity.Order;
import com.example.transport_company.entity.Status;
import com.example.transport_company.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private StatusService statusService;

    @CachePut(value = "order")
    public void save(Order order) {
        RestPreconditions.checkResource(order);
        Status status = statusService.findById(1);
        order.setStatus(status.getName());
        orderRepository.save(order);
    }

    @Cacheable(cacheNames = {"allOrders", "order"}, key = "#id")
    public Order findById(long id) {
        return RestPreconditions.checkResource(orderRepository.findOrderById(id));
    }

    @Cacheable(value = "allOrders", key = "#id")
    public List<Order> findAllByStatusId(long id) {
        Status status = statusService.findById(id);
        return RestPreconditions.checkResource(orderRepository.findAllByStatus(status.getName()));
    }

    @CacheEvict(cacheNames = {"allOrders", "order"}, key = "#orderId")
    public void updateOrderStatus(long orderId, long statusId) {
        findById(orderId);
        Status status = statusService.findById(statusId);
        orderRepository.updateStatus(status.getName(), orderId);
    }

    @Cacheable(value = "allOrders", key = "#email")
    public Set<Order> getAllByEmail(String email) {
        return RestPreconditions.checkResource(orderRepository.findAllByEmail(email));
    }

    @Cacheable(value = "allOrders")
    public List<Order> findAll() {
        return RestPreconditions.checkResource(orderRepository.findAll());
    }

    @CacheEvict(cacheNames = {"allOrders", "order"}, key = "#id")
    public void deleteById(long id) {
        RestPreconditions.checkResource(findById(id));
        orderRepository.deleteById(id);
    }
}
