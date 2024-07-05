package com.mini_project_event_management.event_management.topics.service.impl;

import com.mini_project_event_management.event_management.exceptions.AlreadyExistException;
import com.mini_project_event_management.event_management.exceptions.DataNotFoundException;
import com.mini_project_event_management.event_management.topics.dto.TopicDto;
import com.mini_project_event_management.event_management.topics.entity.Topic;
import com.mini_project_event_management.event_management.topics.repository.TopicsRepository;
import com.mini_project_event_management.event_management.topics.service.TopicsService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicsServiceImpl implements TopicsService {
   private final TopicsRepository topicsRepository;
   public TopicsServiceImpl(TopicsRepository topicsRepository){
       this.topicsRepository = topicsRepository;
   }
   Instant now = Instant.now();
    @Override
    public TopicDto addTopic(TopicDto topicDto){
      Optional<Topic>  checkTopic = Optional.ofNullable(topicsRepository.findByName(topicDto.getName()));
       if(checkTopic.isPresent()){
           throw new AlreadyExistException("Topic name already exist");
       }
       Topic topic = new Topic();
       topic.setName(topicDto.getName());
       topic.setImgUrl(topicDto.getImgUrl());
       topic.setCreatedAt(now);
       topic.setUpdatedAt(now);
       topicsRepository.save(topic);
       TopicDto returnTopic = new TopicDto();
       returnTopic.setName(topic.getName());
       returnTopic.setImgUrl(topic.getImgUrl());
       return  returnTopic;
   }

   @Override
    public List<TopicDto> getAllTopics(){
        List<Topic> topics = topicsRepository.findAll();
        if(topics == null  || topics.isEmpty() ){
            throw new DataNotFoundException("Topics not found");
        }
        return topics.stream().map(this::convertToDto).collect(Collectors.toList());
   }

   @Override
   @Cacheable(value = "getTopicById", key = "#id")
   public Topic getTopicById(Long id){
        Optional<Topic> topic = topicsRepository.findById(id);
        if(topic.isEmpty()){
            throw new DataNotFoundException("topic not found");
        }

        return topic.orElse(null);
   }

   private TopicDto convertToDto(Topic topic){
        TopicDto dto = new TopicDto();
        dto.setName(topic.getName());
        dto.setImgUrl(topic.getImgUrl());
        return dto;
   }
}
