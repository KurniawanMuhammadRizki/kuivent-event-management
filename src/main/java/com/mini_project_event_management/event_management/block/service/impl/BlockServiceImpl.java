package com.mini_project_event_management.event_management.block.service.impl;

import com.mini_project_event_management.event_management.block.dto.BlockDto;
import com.mini_project_event_management.event_management.block.entity.Block;
import com.mini_project_event_management.event_management.block.repository.BlockRepository;
import com.mini_project_event_management.event_management.block.service.BlockService;
import com.mini_project_event_management.event_management.category.entity.Category;
import com.mini_project_event_management.event_management.category.service.CategoryService;
import com.mini_project_event_management.event_management.exceptions.DataNotFoundException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

     @Override
     @Cacheable(value = "getBlockById", key = "#id")
     public Block getBlockById(Long id){
          Optional<Block> block = blockRepository.findById(id);
          if(block.isEmpty() || block == null){
               throw new DataNotFoundException("Block not found");
          }
          return block.orElse(null);
     }

     @Override
     public boolean toggleBlockById(Long id) {
          Block block = getBlockById(id);
          boolean currentStatus = block.isBooked();
          block.setBooked(!currentStatus);
          blockRepository.save(block);
          return block.isBooked();
     }

     @Override
     public List<BlockDto> getBlockByCategoryId(Long id){
          List<Block> blocks = blockRepository.findAllByCategoryId(id);
          if(blocks == null || blocks.isEmpty()){
               throw  new DataNotFoundException("Block not found");
          }
          return blocks.stream().map(Block::toBlockDto).collect(Collectors.toList());
     }
}
