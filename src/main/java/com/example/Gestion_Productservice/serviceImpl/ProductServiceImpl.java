package com.example.Gestion_Productservice.serviceImpl;

import com.example.Gestion_Productservice.entities.Product;
import com.example.Gestion_Productservice.repositories.ProductRepository;
import com.example.Gestion_Productservice.services.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;

    @Override
    public Product getProductById(Long id) {
       return productRepository.findProductById(id);
    }

    @Override
    public Product getProductByLabel(String label) {
        return productRepository.findProductByLabel(label);
    }

    @Override
    public Collection<Product> getAllProduct() {

        return productRepository.findAll();
    }

    @Override
    public Product addProduct(Product product) {

        Product product1=productRepository.findProductByLabel(product.getLabel());

        if(product1==null){
            product.setCreatedAt(new Date());
            product.setPrix_TT(product.getPrix_HT()+(product.getPrix_HT()*20));
            return productRepository.save(product);
        }else {
            return new Product();
        }

    }

    @Override
    public Product updateProduct(Product product) {
        Product product1=productRepository.findProductById(product.getId());

        if(product1!=null){
            product.setModifiedAt(new Date());
            return productRepository.save(product);
        }else {
            return new Product();
        }
    }

    @Override
    public Product deleteProduct(Long id) {
        Product product = productRepository.findProductById(id);

        if(product!=null){
            productRepository.deleteById(id);
            return product;
        }else {
            return new Product();
        }
    }
}
