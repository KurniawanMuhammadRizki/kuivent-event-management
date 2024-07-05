package com.mini_project_event_management.event_management.eventCompany.service.impl;

import com.mini_project_event_management.event_management.company.dto.CompanyDto;
import com.mini_project_event_management.event_management.company.entity.Company;
import com.mini_project_event_management.event_management.company.service.CompanyService;
import com.mini_project_event_management.event_management.event.entity.Event;
import com.mini_project_event_management.event_management.event.service.EventService;
import com.mini_project_event_management.event_management.eventCompany.dto.EventCompanyRequestDto;
import com.mini_project_event_management.event_management.eventCompany.dto.EventCompanyResponseDto;
import com.mini_project_event_management.event_management.eventCompany.entity.EventCompany;
import com.mini_project_event_management.event_management.eventCompany.repository.EventCompanyRepository;
import com.mini_project_event_management.event_management.eventCompany.service.EventCompanyService;
import com.mini_project_event_management.event_management.exceptions.AlreadyExistException;
import com.mini_project_event_management.event_management.exceptions.DataNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventCompanyServiceImpl implements EventCompanyService {

    private final EventCompanyRepository eventCompanyRepository;
    private final CompanyService companyService;
    private final EventService eventService;

    public EventCompanyServiceImpl(EventCompanyRepository eventCompanyRepository, CompanyService companyService, EventService eventService){
        this.eventCompanyRepository= eventCompanyRepository;
        this.companyService = companyService;
        this.eventService = eventService;
    }
    Instant now = Instant.now();
    @Override
    public void addEventCompany(EventCompanyRequestDto eventCompanyRequestDto){
        Company company = companyService.getCompanyById(eventCompanyRequestDto.getCompanyId());
        Event event = eventService.getEventById(eventCompanyRequestDto.getEventId());

        Boolean isCompanyExist = eventCompanyRepository.existsByCompanyId(eventCompanyRequestDto.getCompanyId());
        if (isCompanyExist){
            throw new AlreadyExistException("Company already exist");
        }

        EventCompany eventCompany = new EventCompany();
        eventCompany.setCompany(company);
        eventCompany.setEvent(event);

        eventCompanyRepository.save(eventCompany);
    }

    @Override
    public List<EventCompanyResponseDto> getEventCompanyByEventId(Long id){
        List<EventCompany> eventCompanies = eventCompanyRepository.findAllByEventId(id);

        if(eventCompanies == null || eventCompanies.isEmpty()){
            throw new DataNotFoundException("Event  not found");
        }

        return eventCompanies.stream().map(this::toEventCompanyResponseDto).collect(Collectors.toList());
    }

    private EventCompanyResponseDto toEventCompanyResponseDto(EventCompany eventCompany){
        CompanyDto companyDto = new CompanyDto();
        EventCompanyResponseDto eventCompanyResponseDto = new EventCompanyResponseDto();
        eventCompanyResponseDto.setCompany(companyDto.toCompanyDto(eventCompany.getCompany()));
        eventCompanyResponseDto.setEventId(eventCompany.getEvent().getId());
        return eventCompanyResponseDto;
    }

}
