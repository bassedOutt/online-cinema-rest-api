package com.epam.spring.homework3.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;


@Data
@Builder
@AllArgsConstructor
public class SeatDto implements EntityDto {

    private static final double VIP_PRICE_COEFFICIENT = 30;

    private Long id;

    private int seatNumber;

    private Boolean isVip;

    @JsonInclude(NON_NULL)
    private TicketDto ticket;

    public boolean isTaken(){
        return ticket!=null;
    }
}
