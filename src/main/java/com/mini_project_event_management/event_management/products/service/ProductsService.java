package com.mini_project_event_management.event_management.products.service;

import com.mini_project_event_management.event_management.products.dto.ProductsDto;
import com.mini_project_event_management.event_management.products.entity.Products;

public interface ProductsService {
    void addProducts(ProductsDto productsDto);
    Products getProductsByName(String name);
    Boolean checkProductsByName(String name);

    ProductsDto getProductBySlug(String slug);

}
