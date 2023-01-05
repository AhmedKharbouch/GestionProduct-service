package com.example.Gestion_Productservice.repositories;

import com.example.Gestion_Productservice.entities.Category;
import com.example.Gestion_Productservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;


public interface CategoryRepository extends JpaRepository<Category,Long> {

   Category findCategoryById(Long id);
   Category findCategoryByNom(String nom);

   @Query("select c from Category c where c.nom like :kw")
   Collection<Category> findCategoryByName(@Param(value = "kw")String nom);
}
