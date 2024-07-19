package com.mini_project_event_management.event_management.tickets.service;

import com.mini_project_event_management.event_management.tickets.dto.AddTicketDto;
import com.mini_project_event_management.event_management.tickets.dto.TicketDto;

import java.util.List;

public interface TicketService {
     void addTicket(AddTicketDto ticketDto);

     List<TicketDto> getTicketByEventId(Long id);

     List<TicketDto> getTicketByUsersId(Long id);

     Integer CountByEventId(Long id);
     Boolean checkTicketByUserIdAndEventId(Long userId, Long eventId);

}
