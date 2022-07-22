package com.epam.spring.homework3.model;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
public class Ticket implements Serializable {

    private static final long serialVersionUID = -2019060900196132235L;

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long price;

    @OneToOne
    @JoinColumn(name = "seat_id", unique = true, referencedColumnName = "id")
    private Seat seat;

}
