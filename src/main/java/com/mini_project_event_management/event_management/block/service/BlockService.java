package com.mini_project_event_management.event_management.block.service;

import com.mini_project_event_management.event_management.block.dto.BlockDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


public interface BlockService {
     void addBlock(BlockDto blockDto);
}
