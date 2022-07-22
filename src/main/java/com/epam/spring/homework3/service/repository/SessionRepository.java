package com.epam.spring.homework3.service.repository;

import com.epam.spring.homework3.model.Seat;
import com.epam.spring.homework3.model.Session;
import com.epam.spring.homework3.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session,Long> {

    @Procedure
    void insert_seats(Long sessionId);
}
