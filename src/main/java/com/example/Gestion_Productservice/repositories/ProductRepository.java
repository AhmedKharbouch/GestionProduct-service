package com.example.Gestion_Productservice.repositories;

import com.example.Gestion_Productservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;


public interface ProductRepository extends JpaRepository<Product,Long> {

   Product findProductById(Long id);
   Product findProductByLabel(String label);
   @Query("select p from Product p where p.category.nom like :kw")
   Collection<Product> findProductByCategoryNom(@Param(value = "kw")String nom);


}
