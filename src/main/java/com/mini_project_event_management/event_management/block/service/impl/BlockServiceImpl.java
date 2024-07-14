package com.mini_project_event_management.event_management.block.service.impl;

import com.mini_project_event_management.event_management.block.dto.BlockDto;
import com.mini_project_event_management.event_management.block.entity.Block;
import com.mini_project_event_management.event_management.block.repository.BlockRepository;
import com.mini_project_event_management.event_management.block.service.BlockService;
import com.mini_project_event_management.event_management.category.entity.Category;
import com.mini_project_event_management.event_management.category.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class BlockServiceImpl implements BlockService {
     private final BlockRepository blockRepository;
     private final CategoryService categoryService;
     public BlockServiceImpl(BlockRepository blockRepository, CategoryService categoryService){
          this.blockRepository = blockRepository;
          this.categoryService = categoryService;
     }

     @Override
     public void addBlock(BlockDto blockDto){
          Category category = categoryService.getCategoryById(blockDto.getCategoryId()).toEntity();
          Block block = blockDto.toEntity();
          block.setCategory(category);
          blockRepository.save(block);
     }
}
