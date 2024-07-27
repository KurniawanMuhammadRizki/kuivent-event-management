package com.mini_project_event_management.event_management.products.repository;

import com.mini_project_event_management.event_management.products.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products, Long> {
    Products findByName(String name);
    Products findBySlug(String Slug);
}
