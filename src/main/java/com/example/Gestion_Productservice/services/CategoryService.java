package com.example.Gestion_Productservice.services;

import com.example.Gestion_Productservice.entities.Product;

import java.util.Collection;

public interface CategoryService {

    Product getCategoryById(Long id);
    Product getCategoryByLabel(String label);
    Collection<Product> getAllCategory();
    Product addCategory(Product product);
    Product updateCategory(Product product);

    Product deleteCategory(Long id);

}
