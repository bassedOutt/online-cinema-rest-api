package com.epam.spring.homework3.service;

import com.epam.spring.homework3.dto.SessionDto;
import com.epam.spring.homework3.dto.TicketDto;

import java.util.List;

public interface SessionService extends CrudService<SessionDto> {

    SessionDto findById(Long id);

    //sorts list of sessions(by name, time or number of available seats)
    List<SessionDto> sortSessions(String sorter, List<SessionDto> sessions);

    //returns list of sessions in some range(today, tomorrow, this week)
    List<SessionDto> findInRange(String range, List<SessionDto> sessions);

    List<SessionDto> findSessionsWithTitle(String title);

    TicketDto buyTicket(Long sessionId, Long userId,Long seatId);

    enum SESSION_SORTERS {
        BY_NAME("name"),
        BY_TIME("time"),
        BY_SEATS("seats");

        private final String sorter;


        SESSION_SORTERS(final String sorter) {
            this.sorter = sorter;
        }

        @Override
        public String toString() {
            return sorter;
        }
    }

    enum SESSION_RANGE {
        BY_NAME("today"),
        BY_TIME("tomorrow"),
        BY_SEATS("week");

        private final String sorter;


        SESSION_RANGE(final String sorter) {
            this.sorter = sorter;
        }

        @Override
        public String toString() {
            return sorter;
        }
    }


}
