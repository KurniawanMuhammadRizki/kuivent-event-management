package com.mini_project_event_management.event_management.tickets.service.impl;

import com.mini_project_event_management.event_management.block.entity.Block;
import com.mini_project_event_management.event_management.block.service.BlockService;
import com.mini_project_event_management.event_management.category.entity.Category;
import com.mini_project_event_management.event_management.category.service.CategoryService;
import com.mini_project_event_management.event_management.event.entity.Event;
import com.mini_project_event_management.event_management.event.service.EventService;
import com.mini_project_event_management.event_management.exceptions.DataNotFoundException;
import com.mini_project_event_management.event_management.tickets.dto.AddTicketDto;
import com.mini_project_event_management.event_management.tickets.dto.TicketDto;
import com.mini_project_event_management.event_management.tickets.entity.Ticket;
import com.mini_project_event_management.event_management.tickets.repository.TicketRepository;
import com.mini_project_event_management.event_management.tickets.service.TicketService;
import com.mini_project_event_management.event_management.users.entity.Users;
import com.mini_project_event_management.event_management.users.service.UsersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;



@Service
public class TicketServiceImpl implements TicketService {
     private final TicketRepository ticketRepository;
     private final EventService eventService;
     private final UsersService usersService;
     private final CategoryService categoryService;
     private final BlockService blockService;

     public TicketServiceImpl(TicketRepository ticketRepository, EventService eventService, UsersService usersService, CategoryService categoryService, BlockService blockService) {
          this.ticketRepository = ticketRepository;
          this.eventService = eventService;
          this.usersService = usersService;
          this.blockService = blockService;
          this.categoryService = categoryService;
     }

//     @Override
//     public void addTicket(TicketDto ticketDto) {
//          Event event = eventService.getEventById(ticketDto.getEventId());
//          Users user = usersService.getUserById(ticketDto.getUserId());
//          Ticket ticket = ticketDto.toTicket();
//          ticket.setEvent(event);
//          ticket.setUsers(user);
//          ticketRepository.save(ticket);
//     }

     @Override
     @Transactional
     public void addTicket(AddTicketDto ticketDto) {
          Event event = eventService.getEventById(ticketDto.getEventId());
          Users user = usersService.getUserById(ticketDto.getUserId());
          Block block = blockService.getBlockById(ticketDto.getBlockId());
          Category category = categoryService.getCategoryById(ticketDto.getCategoryId()).toEntity();
          String ticketCode = generateTicketCode(user.getFirstName());
          Ticket ticket = new Ticket();
          ticket.setEvent(event);
          ticket.setEventName(event.getName());
          ticket.setDateStart(event.getDateStart().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
          ticket.setDateEnd(event.getDateEnd().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
          ticket.setHourStart(event.getHourStart().toInstant().atZone(ZoneId.systemDefault()).toLocalTime());
          ticket.setHourEnd(event.getHourEnd().toInstant().atZone(ZoneId.systemDefault()).toLocalTime());
          ticket.setCity(event.getCity());
          ticket.setEventType(event.getEventType().getName());
          ticket.setUsers(user);
          ticket.setFirstName(user.getFirstName());
          ticket.setLastName(user.getLastName());
          ticket.setEmail(user.getEmail());
          ticket.setCategory(category);
          ticket.setCategoryName(category.getName());
          ticket.setBlock(block);
          ticket.setBlockName(block.getName());
          ticket.setTicketCode(ticketCode);
          ticketRepository.save(ticket);
     }

     @Override
     public List<TicketDto> getTicketByEventId(Long id) {
          List<Ticket> tickets = ticketRepository.findAllByEventId(id);
          if (tickets == null || tickets.isEmpty()) {
               throw new DataNotFoundException("Ticket not found");
          }
          return tickets.stream().map(Ticket::toTicketDto).collect(Collectors.toList());
     }

     @Override
     public List<TicketDto> getTicketByUsersId(Long id) {
          List<Ticket> tickets = ticketRepository.findAllByUsersId(id);
          if (tickets == null || tickets.isEmpty()) {
               throw new DataNotFoundException("Ticket not found");
          }
          return tickets.stream().map(Ticket::toTicketDto).collect(Collectors.toList());
     }

     private String generateTicketCode(String firstName) {
          SecureRandom random = new SecureRandom();
          String letter = firstName.length() < 3 ? firstName : firstName.substring(0, 3);
          int number = 100 + random.nextInt(900);
          return letter.toUpperCase() + number;
     }

     @Override
     public Integer CountByEventId(Long id){
          Event event = eventService.getEventById(id);
          Integer result = ticketRepository.countByEventId(event.getId());
          return result;

     }


}
