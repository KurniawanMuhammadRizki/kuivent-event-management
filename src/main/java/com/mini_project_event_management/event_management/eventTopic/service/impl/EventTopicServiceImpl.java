package com.mini_project_event_management.event_management.eventTopic.service.impl;

import com.mini_project_event_management.event_management.event.entity.Event;
import com.mini_project_event_management.event_management.event.service.EventService;
import com.mini_project_event_management.event_management.eventCompany.dto.EventCompanyResponseDto;
import com.mini_project_event_management.event_management.eventTopic.dto.EventTopicAddRequestDto;
import com.mini_project_event_management.event_management.eventTopic.dto.EventTopicResponseDto;
import com.mini_project_event_management.event_management.eventTopic.entity.EventTopic;
import com.mini_project_event_management.event_management.eventTopic.repository.EventTopicRepository;
import com.mini_project_event_management.event_management.eventTopic.service.EventTopicService;
import com.mini_project_event_management.event_management.exceptions.AlreadyExistException;
import com.mini_project_event_management.event_management.exceptions.DataNotFoundException;
import com.mini_project_event_management.event_management.topics.dto.TopicDto;
import com.mini_project_event_management.event_management.topics.entity.Topic;
import com.mini_project_event_management.event_management.topics.service.TopicsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventTopicServiceImpl implements EventTopicService {
    private final EventTopicRepository eventTopicRepository;
    private final TopicsService topicsService;
    private final EventService eventService;

    public EventTopicServiceImpl(EventTopicRepository eventTopicRepository , TopicsService topicsService, EventService eventService){
        this.eventTopicRepository = eventTopicRepository;
        this.topicsService = topicsService;
        this.eventService = eventService;
    }

    @Override
    public void addEventTopic(EventTopicAddRequestDto eventTopicAddRequestDto){
        Topic topic = topicsService.getTopicById(eventTopicAddRequestDto.getTopicId());
        Event event = eventService.getEventById(eventTopicAddRequestDto.getEventId());

        Boolean isTopicExist = eventTopicRepository.existsByTopicId(eventTopicAddRequestDto.getTopicId());
        if(isTopicExist){
            throw new AlreadyExistException("Topic already exist");
        }

        EventTopic eventTopic = new EventTopic();
        eventTopic.setTopic(topic);
        eventTopic.setEvent(event);

        eventTopicRepository.save(eventTopic);
    }

    @Override
    public List<EventTopicResponseDto> getEventTopicByEventId(Long id){
        List<EventTopic> eventTopics = eventTopicRepository.findAllByEventId(id);

        if (eventTopics == null || eventTopics.isEmpty()){
            throw new DataNotFoundException("Event Topic not found");
        }

        return eventTopics.stream().map(this::toEventTopicResponseDto).collect(Collectors.toList());
    }

    private EventTopicResponseDto toEventTopicResponseDto (EventTopic eventTopic){
        TopicDto topicDto = new TopicDto();
         EventTopicResponseDto eventTopicResponseDto = new EventTopicResponseDto();

        eventTopicResponseDto.setEventId(eventTopic.getEvent().getId());
        eventTopicResponseDto.setTopic(topicDto.toTopicDto(eventTopic.getTopic()));

        return eventTopicResponseDto;
    }

}
