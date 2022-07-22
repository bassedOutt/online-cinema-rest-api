package com.epam.spring.homework3.dto.mapper;

import com.epam.spring.homework3.dto.*;
import com.epam.spring.homework3.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;


//had issues with including mappers, so decided to go with just 1 mapper for all entities
@Mapper
public interface EntityMapper {

    EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);

    PricingDto pricingToPricingDto(Pricing pricing);

    Pricing fromPricingDto(PricingDto pricing);

    @Named("toTicketDto")
    @Mapping(target = "seat", qualifiedByName = "toSeatDtoWithoutTicket")
    TicketDto ticketToTicketDto(Ticket ticket);

    @Named("toTicketDtoWithoutSeat")
    @Mapping(target = "seat", ignore = true)
    TicketDto toTicketDtoWithoutSeat(Ticket ticket);

    @Named("fromTicketDto")
    @Mapping(target = "seat", qualifiedByName = "toSeatWithoutTicket")
    Ticket fromTicketDto(TicketDto ticketDto);

    @Named(value = "toTicketWithoutSeat")
    @Mapping(target = "seat", ignore = true)
    Ticket toTicketWithoutSeat(TicketDto ticketDto);

    @Named("toSeatDto")
    @Mapping(target = "ticket", qualifiedByName = "toTicketDtoWithoutSeat")
    SeatDto seatToSeatDto(Seat seat);

    @Named(value = "toSeatDtoWithoutTicket")
    @Mapping(target = "ticket", ignore = true)
    SeatDto toSeatDtoWithoutTicket(Seat seat);

    @Named(value = "toSeatWithoutTicket")
    @Mapping(target = "ticket", ignore = true)
    Seat toSeatWithoutTicket(SeatDto seatDto);

    @Named(value = "fromSeatDto")
    @Mapping(target = "ticket", qualifiedByName = "toTicketWithoutSeat")
    Seat fromSeatDto(SeatDto seatDto);

    @Named(value = "toSessionDtoListWithoutMovie")
    default List<SessionDto> toSessionDtoListWithoutMovie(List<Session> list) {
        return list == null ? null :
                list.stream()
                        .map(this::toSessionDtoWithoutMovie)
                        .collect(Collectors.toList());
    }

    @Named(value = "toSeatDtoListWithoutTicket")
    default List<SeatDto> toSeatDtoListWithoutTicket(List<Seat> list) {
        return list == null ? null :
                list.stream()
                        .map(this::toSeatDtoWithoutTicket)
                        .collect(Collectors.toList());
    }

    default List<SessionDto> sessionListToSessionDtoList(List<Session> list) {
        return list == null ? null :
                list.stream()
                        .map(this::sessionToSessionDto)
                        .collect(Collectors.toList());
    }

    @Named(value = "toSessionDtoWithoutMovie")
    @Mapping(target = "movie", ignore = true)
    @Mapping(target = "seats", qualifiedByName = "toSeatDtoListWithoutTicket")
    @Mapping(target = "tickets", qualifiedByName = "toTicketDtoListWithoutSeat")
    SessionDto toSessionDtoWithoutMovie(Session session);

    Session fromSessionDto(SessionDto sessionDto);

    @Named("toSessionDto")
    @Mapping(target = "movie", qualifiedByName = "toMovieDtoWithoutSessions")
    @Mapping(target = "seats", qualifiedByName = "toSeatDtoListWithoutTicket")
    @Mapping(target = "tickets", qualifiedByName = "toTicketDtoListWithoutSeat")
    SessionDto sessionToSessionDto(Session session);

    @Named("toMovieDto")
    @Mapping(target = "sessionList", qualifiedByName = "toSessionDtoListWithoutMovie")
    MovieDto movieToMovieDto(Movie movie);

    @Named(value = "toMovieDtoWithoutSessions")
    @Mapping(target = "sessionList", ignore = true)
    MovieDto toMovieDtoWithoutSessions(Movie movie);

    Movie fromMovieDto(MovieDto movieDto);

    UserDto toUserDto(User user);

    User fromUserDto(UserDto user);

    @Named("toTicketDtoListWithoutSeat")
    default List<TicketDto> toTicketDtoListWithoutSeat(List<Ticket> list) {
        return list == null ? null :
                list.stream().map(this::toTicketDtoWithoutSeat)
                        .collect(Collectors.toList());
    }


}
