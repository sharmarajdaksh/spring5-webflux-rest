package com.example.spring5webfluxrest.bootstrap;

import com.example.spring5webfluxrest.domain.Category;
import com.example.spring5webfluxrest.domain.Vendor;
import com.example.spring5webfluxrest.repositories.CategoryRepository;
import com.example.spring5webfluxrest.repositories.VendorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.vendorRepository = vendorRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count().block() == 0) {
            bootstrapCategoriesData();
            bootstrapVendorData();
        } else {
            log.info("Database contains data, skipping bootstrapping");
        }
    }

    private void bootstrapCategoriesData() {
        categoryRepository.save(Category.builder().description("Fruits").build()).block();
        categoryRepository.save(Category.builder().description("Nuts").build()).block();
        categoryRepository.save(Category.builder().description("Breads").build()).block();
        categoryRepository.save(Category.builder().description("Meats").build()).block();
        categoryRepository.save(Category.builder().description("Eggs").build()).block();

        log.info("Boostrapped category data with " + categoryRepository.count().block() + " documents");
    }

    private void bootstrapVendorData() {
        vendorRepository.save(Vendor.builder().firstName("John").lastName("Doe").build()).block();
        vendorRepository.save(Vendor.builder().firstName("Dakshraj").lastName("Sharma").build()).block();
        vendorRepository.save(Vendor.builder().firstName("Dex").lastName("S").build()).block();
        vendorRepository.save(Vendor.builder().firstName("Bill").lastName("Nershi").build()).block();

        log.info("Boostrapped vendor data with " + vendorRepository.count().block() + " documents");
    }
}
