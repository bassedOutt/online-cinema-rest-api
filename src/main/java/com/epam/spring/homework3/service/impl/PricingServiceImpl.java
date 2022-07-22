package com.epam.spring.homework3.service.impl;

import com.epam.spring.homework3.dto.PricingDto;
import com.epam.spring.homework3.dto.mapper.EntityMapper;
import com.epam.spring.homework3.exception.EntityNotFoundException;
import com.epam.spring.homework3.model.Pricing;
import com.epam.spring.homework3.service.PricingService;
import com.epam.spring.homework3.service.repository.PricingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
class PricingServiceImpl implements PricingService {

    private PricingRepository repository;
    private static final EntityMapper mapper = EntityMapper.INSTANCE;

    @Autowired
    public void setRepository(PricingRepository repository) {
        this.repository = repository;
    }

    public List<PricingDto> findAll() {
        log.info("getting list of pricings");
        return repository.findAll().stream()
                .map(mapper::pricingToPricingDto)
                .collect(Collectors.toList());
    }

    public PricingDto getById(Long id) {
        log.info("getting pricing with id {} ", id);
        return mapper.pricingToPricingDto(repository.getById(id));
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    public PricingDto insert(PricingDto entity) {
        log.info("inserting pricing: {}", entity);
        Pricing pricing = mapper.fromPricingDto(entity);
        repository.save(pricing);
        return entity;
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    public PricingDto update(PricingDto entity) {
        log.info("updating pricing: {}", entity);
        Pricing pricing = mapper.fromPricingDto(entity);
        repository.save(pricing);
        return entity;
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    public void delete(Long id) {
        log.info("deleting user with an id: {}", id);
        repository.deleteById(id);
    }

    @Override
    public PricingDto findByName(String name) {
        log.info("Searching for pricing with name: {}", name);
        return repository.findAll().stream()
                .filter(pricing -> pricing.getName().equals(name))
                .findFirst()
                .map(mapper::pricingToPricingDto)
                .orElseThrow(() -> new EntityNotFoundException("No pricing with name " + name + "were found"));
    }
}
