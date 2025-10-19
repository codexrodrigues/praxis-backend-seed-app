package com.example.praxisseed.config;

import com.example.praxisseed.domain.Category;
import com.example.praxisseed.domain.Product;
import com.example.praxisseed.repository.CategoryRepository;
import com.example.praxisseed.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner seedData(CategoryRepository categories, ProductRepository products) {
        return args -> {
            if (categories.count() > 0 || products.count() > 0) return;

            Category hardware = new Category();
            hardware.setName("Hardware");
            hardware.setDescription("Peças e periféricos");
            categories.save(hardware);

            Category software = new Category();
            software.setName("Software");
            software.setDescription("Licenças e serviços");
            categories.save(software);

            Product keyboard = new Product();
            keyboard.setName("Teclado Mecânico");
            keyboard.setPrice(new BigDecimal("399.90"));
            keyboard.setCategory(hardware);
            products.save(keyboard);

            Product office = new Product();
            office.setName("Pacote Office");
            office.setPrice(new BigDecimal("899.00"));
            office.setCategory(software);
            products.save(office);
        };
    }
}

