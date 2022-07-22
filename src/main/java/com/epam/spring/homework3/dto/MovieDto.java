package com.epam.spring.homework3.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@ToString(exclude = {"sessionList"})
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto implements EntityDto {

    private Long id;

    @Min(value = 10, message = "Movie duration can not be less than 10 min")
    private int duration;

    @NotEmpty(message = "Image url can not be empty")
    private String imageUrl;

    @Min(value = 1, message = "Price can not be 0 or less")
    private int price;

    private String uaTitle;
    private String uaDescription;

    private String enTitle;
    private String enDescription;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<SessionDto> sessionList;

}
