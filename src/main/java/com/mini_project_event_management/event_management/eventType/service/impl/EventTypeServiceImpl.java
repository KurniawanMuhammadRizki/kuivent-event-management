package com.mini_project_event_management.event_management.eventType.service.impl;

import com.mini_project_event_management.event_management.eventType.entity.EventType;
import com.mini_project_event_management.event_management.eventType.repository.EventTypeRepository;
import com.mini_project_event_management.event_management.eventType.service.EventTypeService;
import com.mini_project_event_management.event_management.exceptions.DataNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventTypeServiceImpl implements EventTypeService {
    private final EventTypeRepository eventTypeRepository;
    public EventTypeServiceImpl(EventTypeRepository eventTypeRepository){
        this.eventTypeRepository = eventTypeRepository;
    }
    @Override
    public EventType getEventTypeById(Integer id){
        Optional<EventType>  eventType = eventTypeRepository.findById(id);
        if(eventType.isEmpty()){
            throw new DataNotFoundException("event type not found");
        }

        return eventType.orElse(null);
    }
}
