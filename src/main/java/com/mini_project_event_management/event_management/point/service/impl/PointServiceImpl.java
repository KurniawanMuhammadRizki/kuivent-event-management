package com.mini_project_event_management.event_management.point.service.impl;

import com.mini_project_event_management.event_management.company.entity.Company;
import com.mini_project_event_management.event_management.company.service.CompanyService;
import com.mini_project_event_management.event_management.exceptions.DataNotFoundException;
import com.mini_project_event_management.event_management.helpers.CurrentUser;
import com.mini_project_event_management.event_management.point.dto.AddPointDto;
import com.mini_project_event_management.event_management.point.dto.PointDto;
import com.mini_project_event_management.event_management.point.entity.Point;
import com.mini_project_event_management.event_management.point.repository.PointRepository;
import com.mini_project_event_management.event_management.point.service.PointService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class PointServiceImpl implements PointService {

    private final PointRepository pointRepository;
    private final CompanyService companyService;
    private  final CurrentUser currentUser;


    public PointServiceImpl(PointRepository pointRepository, CompanyService companyService, CurrentUser currentUser){
        this.pointRepository = pointRepository;
        this.companyService = companyService;
        this.currentUser = currentUser;
    }

    Instant now = Instant.now();
    @Override
    @Transactional
    public void addPoint( Long companyId){
        Company company = companyService.getCompanyById(companyId);
        //Instant expiredAt = now.plus(3, ChronoUnit.MONTHS);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(now, ZoneId.systemDefault());

        // Add 3 months to LocalDateTime
        LocalDateTime futureDateTime = localDateTime.plus(3, ChronoUnit.MONTHS);

        // Convert back to Instant
        Instant expiredAt = futureDateTime.atZone(ZoneId.systemDefault()).toInstant();

        Point newPoint = new Point();
        newPoint.setCompany(company);
        newPoint.setPoint(10000);
        newPoint.setCreatedAt(now);
        newPoint.setUpdatedAt(now);
        newPoint.setExpiredAt(expiredAt);
        pointRepository.save(newPoint);
    }
}
