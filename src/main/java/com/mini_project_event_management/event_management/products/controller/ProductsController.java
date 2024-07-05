package com.mini_project_event_management.event_management.products.controller;

import com.mini_project_event_management.event_management.products.dto.ProductsDto;
import com.mini_project_event_management.event_management.products.service.ProductsService;
import com.mini_project_event_management.event_management.responses.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {
    private final ProductsService productsService;

    public ProductsController(ProductsService productsService){
        this.productsService = productsService;
    }

    @PostMapping
    public ResponseEntity<Response<Object>> addProducts(@Validated @RequestBody ProductsDto productsDto){
        productsService.addProducts(productsDto);
        return  Response.successfulResponse("ok");
    }


}
