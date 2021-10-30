package com.example.transport_company.entity;

import com.example.transport_company.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "contact_email", unique = true)
    @Pattern(regexp = Constants.EMAIL_PATTERN, message = "Wrong email input")
    private String email;

    @Column(name = "password", unique = true)
    @Size(min = 8, message = "Password should be stronger")
    private String password;

    @ManyToOne
    private Role roles;

    @OneToOne
    private Status status;

}
