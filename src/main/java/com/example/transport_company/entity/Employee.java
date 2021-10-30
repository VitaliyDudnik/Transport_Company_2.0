package com.example.transport_company.entity;

import com.example.transport_company.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "full_name")
    private String FullName;

    @Column(name = "contact_email", unique = true)
    @Pattern(regexp = Constants.EMAIL_PATTERN)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "contract_number")
    private String contractNumber;

    @Column(name = "postal_code")
    private String postalCode;

    @ManyToOne
    private Department department;
}
