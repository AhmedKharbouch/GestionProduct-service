package com.example.Gestion_Productservice.services;

import com.example.Gestion_Productservice.entities.Product;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

public interface ProductService {

    Product getProductById(Long id);
    Product getProductByLabel(String label);
    Collection<Product> getAllProduct();
    Product addProduct(Product product);
    Product updateProduct(Product product);

    Product deleteProduct(Long id);

}
