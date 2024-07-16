package com.mini_project_event_management.event_management.block.repository;

import com.mini_project_event_management.event_management.block.entity.Block;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlockRepository extends JpaRepository<Block, Long> {
     List<Block> findAllByCategoryId(Long id);
}
