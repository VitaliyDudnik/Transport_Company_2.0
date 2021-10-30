package com.example.transport_company.repository;

import com.example.transport_company.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Transactional
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Transactional(readOnly = true)
    List<Order> findAllByStatus(String status);

    @Modifying
    @Query("update Order o set o.status= :statusName where o.id = :orderId")
    void updateStatus(@Param("statusName") String statusName, @Param("orderId") long orderId);

    @Transactional(readOnly = true)
    Set<Order> findAllByEmail(String email);

    @Transactional(readOnly = true)
    Order findOrderById(long id);

}
