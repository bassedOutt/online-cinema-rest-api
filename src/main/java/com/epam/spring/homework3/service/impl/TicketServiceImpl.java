package com.epam.spring.homework3.service.impl;

import com.epam.spring.homework3.dto.TicketDto;
import com.epam.spring.homework3.dto.mapper.EntityMapper;
import com.epam.spring.homework3.model.Ticket;
import com.epam.spring.homework3.service.TicketService;
import com.epam.spring.homework3.service.repository.TicketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TicketServiceImpl implements TicketService {

    private TicketRepository repository;
    private static final EntityMapper mapper = EntityMapper.INSTANCE;

    @Autowired
    public void setRepository(TicketRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TicketDto> findAll() {
        log.info("getting list of tickets");
        return repository.findAll().stream()
                .map(mapper::ticketToTicketDto)
                .collect(Collectors.toList());
    }

    @Override
    public TicketDto insert(TicketDto entity) {
        log.info("inserting ticket: {}", entity);
        Ticket ticket = mapper.fromTicketDto(entity);
        repository.save(ticket);
        return mapper.ticketToTicketDto(ticket);
    }

    @Override
    public TicketDto update(TicketDto entity) {
        log.info("updating ticket with id: {}", entity.getId());
        Ticket ticket = mapper.fromTicketDto(entity);
        repository.save(ticket);
        return entity;
    }

    @Override
    public void delete(Long id) {
        log.info("deleting ticket with id: {}", id);
        repository.deleteById(id);
    }

}
