package com.epam.spring.homework3.service;

import com.epam.spring.homework3.dto.SeatDto;

public interface SeatService extends CrudService<SeatDto> {
    SeatDto findById(Long id);
}
