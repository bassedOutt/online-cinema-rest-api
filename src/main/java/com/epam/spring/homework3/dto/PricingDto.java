package com.epam.spring.homework3.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
public class PricingDto implements EntityDto {

    private Long id;

    @NotEmpty(message = "Pricing name can not be null")
    private String name;

    @Min(value = 1,message = "Price can't be 0 or less")
    private Long price;
}
