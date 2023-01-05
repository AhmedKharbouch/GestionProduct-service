package com.example.Gestion_Productservice;

import com.example.Gestion_Productservice.entities.Category;
import com.example.Gestion_Productservice.entities.Product;
import com.example.Gestion_Productservice.repositories.CategoryRepository;
import com.example.Gestion_Productservice.repositories.ProductRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
@OpenAPIDefinition
public class GestionProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionProductServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductRepository productRepository, CategoryRepository categoryRepository){
		return args -> {
			productRepository.save(new Product(null,"samsung",5550,0,10,new Date(),null,null));
			productRepository.save(new Product(null,"iphone",8896,0,20,new Date(),null,null));
			productRepository.save(new Product(null,"huawei",2200,0,30,new Date(),null,null));

			productRepository.findAll().forEach(c->{
				System.out.println(c.toString());
			});

			categoryRepository.save(new Category(null,"voiture",new Date(),null));
			categoryRepository.save(new Category(null,"telephone",new Date(),null));
			categoryRepository.save(new Category(null,"menage",new Date(),null));
			categoryRepository.findAll().forEach(c->{
				System.out.println(c.toString());
			});
		};
	}




}
