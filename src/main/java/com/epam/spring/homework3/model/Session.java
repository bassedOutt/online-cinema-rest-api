package com.epam.spring.homework3.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString(exclude = {"movie"})
@Entity
public class Session{

    public Session(){

    }

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @ManyToOne
    @JoinColumn(name = "pricing_id",columnDefinition = "bigint default 1")
    private Pricing pricing;

    @OneToMany
    @JoinColumn(name = "session_id", referencedColumnName = "id")
    private List<Seat> seats;

    @OneToMany
    @JoinColumn(name = "session_id", referencedColumnName = "id")
    private List<Ticket> tickets;

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

}
