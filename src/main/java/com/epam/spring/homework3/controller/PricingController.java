package com.epam.spring.homework3.controller;

import com.epam.spring.homework3.dto.PricingDto;
import com.epam.spring.homework3.service.PricingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("api/v1/pricing")
public class PricingController {

    private PricingService service;

    @Autowired
    public void setUserService(PricingService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<PricingDto> getAllPricings(){
        log.info("getting list of pricings");
        return service.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "{name}")
    public PricingDto getPricingByName(@PathVariable String name){
        log.info("getting pricing by name: {}", name);
        return service.findByName(name);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PricingDto createPricing(@RequestBody @Valid PricingDto pricingDto){
        log.info("creating pricing : {}",pricingDto);
        return service.insert(pricingDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public PricingDto updatePricing(@RequestBody @Valid PricingDto pricingDto){
        log.info("updating pricing with id : {}",pricingDto.getId());
        return service.update(pricingDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deletePricing(@PathVariable Long id){
        log.info("deleting pricing with id: {}",id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
