package com.mini_project_event_management.event_management.block.controller;

import com.mini_project_event_management.event_management.block.dto.BlockDto;
import com.mini_project_event_management.event_management.block.service.BlockService;
import com.mini_project_event_management.event_management.responses.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/block")
public class BlockController {
     private final BlockService blockService;
     public BlockController(BlockService blockService){
          this.blockService = blockService;
     }

     @PostMapping
     public ResponseEntity<Response<Object>> addBlock(@Validated @RequestBody BlockDto blockDto){
           blockService.addBlock(blockDto);
          return Response.successfulResponse("Block Added successfully");
     }

     @PostMapping("/{id}")
     public ResponseEntity<Response<Boolean>> toggleBlockById(@PathVariable Long id){
          boolean status = blockService.toggleBlockById(id);
          return  Response.successfulResponse("Changed successfully, current status: ", status);
     }

     @GetMapping("/category/{id}")
     public ResponseEntity<Response<List<BlockDto>>> getBlockByCategoryId(@PathVariable Long id){
          var blocks = blockService.getBlockByCategoryId(id);
          return Response.successfulResponse("Blocks fetched successfully",  blocks );
     }


}
