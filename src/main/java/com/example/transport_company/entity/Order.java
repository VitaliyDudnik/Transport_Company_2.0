package com.example.transport_company.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "shipping_date")
    private LocalDate shippingDate;

    @Column(name = "shipping_weight")
    private String shippingWeight;

    @Column(name = "shipping_street")
    private String shippingStreet;

    @Column(name = "shipping_city")
    private String shippingCity;

    @Column(name = "shipping_country")
    private String shippingCountry;

    @Column(name = "shipping_PostalCode")
    private String shippingPostalCode;

    @Column(name = "email")
    private String email;

    @Column(name = "status")
    private String status;

}
