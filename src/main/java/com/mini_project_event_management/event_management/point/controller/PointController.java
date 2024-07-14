package com.mini_project_event_management.event_management.point.controller;

import com.mini_project_event_management.event_management.point.dto.AddPointDto;
import com.mini_project_event_management.event_management.point.dto.PointDto;
import com.mini_project_event_management.event_management.point.service.PointService;
import com.mini_project_event_management.event_management.responses.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/point")
public class PointController {
    private final PointService pointService;
    public PointController(PointService pointService){
        this.pointService = pointService;
    }

    @PostMapping("{id}")
    public ResponseEntity<Response<Integer>> getPointsByCompanyId(@PathVariable Long id){
        var totalPoint = pointService.getPointsByCompanyId(id);
        return Response.successfulResponse("Total point :", totalPoint);
    }

//    @PostMapping
//    public ResponseEntity<Response<PointDto>> addPoint(@Validated @RequestBody AddPointDto addPointDto){
//
//    }

}
