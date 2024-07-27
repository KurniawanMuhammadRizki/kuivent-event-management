package com.mini_project_event_management.event_management.products.service.impl;

import com.mini_project_event_management.event_management.company.entity.Company;
import com.mini_project_event_management.event_management.company.service.CompanyService;
import com.mini_project_event_management.event_management.exceptions.DataNotFoundException;
import com.mini_project_event_management.event_management.helpers.CurrentUser;
import com.mini_project_event_management.event_management.helpers.SlugifyHelper;
import com.mini_project_event_management.event_management.products.dto.ProductsDto;
import com.mini_project_event_management.event_management.products.entity.Products;
import com.mini_project_event_management.event_management.products.repository.ProductsRepository;
import com.mini_project_event_management.event_management.products.service.ProductsService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Service
public class ProductsServiceImpl implements ProductsService {

    private final ProductsRepository productsRepository;
    private final CompanyService companyService;
    private final CurrentUser currentUser;

    public ProductsServiceImpl(ProductsRepository productsRepository, CompanyService companyService, CurrentUser currentUser){
        this.productsRepository = productsRepository;
        this.companyService = companyService;
        this.currentUser = currentUser;
    }

    Instant now = Instant.now();



    @Override
    @Transactional
    public void addProducts(ProductsDto productsDto){
        Long companyId = currentUser.getAuthorizedCompanyId();
        Company company = companyService.getCompanyById(companyId);
        Boolean checkProductName = checkProductsByName(productsDto.getName());
        String slug = SlugifyHelper.slugify(productsDto.getName());

        Products products = new Products();
        products.setCompany(company);
        products.setSlug(slug);
        products.setName(productsDto.getName());
        products.setDescription(productsDto.getDescription());
        products.setImageUrl(productsDto.getImageUrl());
        products.setCreatedAt(now);
        products.setUpdatedAt(now);

        productsRepository.save(products);
    }

    @Override
    @Cacheable(value = "getProductsByName", key = "#name")
    public Products getProductsByName(String name){
        Optional<Products> products = Optional.ofNullable(productsRepository.findByName(name));

        if(products.isEmpty()){
            throw new DataNotFoundException("Product not found");
        }
        return products.orElse(null);
    }

    @Override
    public ProductsDto getProductBySlug(String slug){
        Optional<Products> products = Optional.ofNullable(productsRepository.findBySlug(slug));

        if(products.isEmpty()){
            throw new DataNotFoundException("Product not found");
        }
        return products.get().toProductsDto();
    }

    @Override
    public Boolean checkProductsByName(String name){
        Optional<Products> products = Optional.ofNullable(productsRepository.findByName(name));
        return products.isPresent();
    }




}
