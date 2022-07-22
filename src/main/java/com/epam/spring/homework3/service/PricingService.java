package com.epam.spring.homework3.service;

import com.epam.spring.homework3.dto.PricingDto;

public interface PricingService extends CrudService<PricingDto> {
    PricingDto findByName(String name);
}
