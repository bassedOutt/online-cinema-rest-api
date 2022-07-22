package com.epam.spring.homework3.dto;

import com.epam.spring.homework3.model.Role;
import com.epam.spring.homework3.validation.EmailConstraint;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@AllArgsConstructor
public class UserDto implements EntityDto {

    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String surname;

    @EmailConstraint
    private String email;

    @NotEmpty
    @Length(min = 6, max = 30)
    private String password;

    @JsonInclude(NON_NULL)
    private List<TicketDto> tickets;

    public void addTicket(TicketDto ticketDto) {
        tickets.add(ticketDto);
    }

    List<Role> roles;

}
