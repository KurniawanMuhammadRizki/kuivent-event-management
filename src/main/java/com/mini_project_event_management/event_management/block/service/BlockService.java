package com.mini_project_event_management.event_management.block.service;

import com.mini_project_event_management.event_management.block.dto.BlockDto;
import com.mini_project_event_management.event_management.block.entity.Block;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


public interface BlockService {
     void addBlock(BlockDto blockDto);
     Block getBlockById(Long id);
     boolean toggleBlockById(Long id);
     List<BlockDto> getBlockByCategoryId(Long id);
}
