package com.mini_project_event_management.event_management.topics.controller;

import com.mini_project_event_management.event_management.responses.Response;
import com.mini_project_event_management.event_management.topics.dto.TopicDto;
import com.mini_project_event_management.event_management.topics.service.TopicsService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/topics")
public class TopicsController {
    private final TopicsService topicsService;

    public TopicsController(TopicsService topicsService){
        this.topicsService = topicsService;
    }

    @PostMapping
    public ResponseEntity<Response<TopicDto>> addTopic(@Validated @RequestBody TopicDto topicDto){
        var createdTopic = topicsService.addTopic(topicDto);
        return Response.successfulResponse("Topic created successfully", createdTopic);
    }

    @GetMapping
    public ResponseEntity<Response<List<TopicDto>>> getTopics(){
        var topics = topicsService.getAllTopics();
   
        return Response.successfulResponse("Topic feched successfully", topics);
    }


}
