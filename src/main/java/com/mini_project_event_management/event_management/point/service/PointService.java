package com.mini_project_event_management.event_management.point.service;

import com.mini_project_event_management.event_management.point.dto.AddPointDto;
import com.mini_project_event_management.event_management.point.dto.PointDto;

public interface PointService {
    void addPoint(Long companyId);
    Integer getPointsByCompanyId(Long companyId);

    int softDeletePointsByCompanyId(Long companyId);

}
