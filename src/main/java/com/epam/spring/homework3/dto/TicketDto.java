package com.epam.spring.homework3.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@AllArgsConstructor
public class TicketDto implements EntityDto {

    private Long id;
    private Long price;

    @JsonInclude(NON_NULL)
    private SeatDto seat;
}
