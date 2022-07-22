package com.epam.spring.homework3.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class Pricing {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true,nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;
}
