package com.example.Gestion_Productservice.webController;

import com.example.Gestion_Productservice.DAO.DaoProduct;
import com.example.Gestion_Productservice.entities.Category;
import com.example.Gestion_Productservice.entities.Product;
import com.example.Gestion_Productservice.entities.Rules;
import com.example.Gestion_Productservice.exceptions.CategoryExistException;
import com.example.Gestion_Productservice.exceptions.ProductExistException;
import com.example.Gestion_Productservice.repositories.CategoryRepository;
import com.example.Gestion_Productservice.repositories.ProductRepository;
import com.example.Gestion_Productservice.repositories.RulesRepository;
import com.example.Gestion_Productservice.services.ProductService;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.common.reflect.TypeToken;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class GestionController {
    //private ProductService productService;
    private RestTemplate restTemplate;

    private  ProductRepository productRepository;

    private RulesRepository rulesRepository;
    private CategoryRepository categoryRepository;


    @GetMapping(path = "/products")
    public Collection<Product> getProducts(){
        return productRepository.findAll();

    }
    @GetMapping(path = "/products/{id}")
    public Product getProductByID(@PathVariable("id")Long id){
        Product productById = productRepository.findProductById(id);
        System.out.println(productById.getCategory().getNom());
        return productById;

    }

    @PostMapping(path = "/addProduct")
    public Product addProduct(@RequestBody Product product) throws ProductExistException {

        Product product1=productRepository.findProductByLabel(product.getLabel());

        if(product1!=null)
            throw  new ProductExistException("product already exist") ;

        product.setCreatedAt(new Date());
        product.setPrix_TT(product.getPrix_HT()+(product.getPrix_HT()*0.2F));
        return productRepository.save(product);

    }
    @PostMapping (path = "/updateProduct")
    public Product updateProduct(@RequestBody Product product){

        Product product1=productRepository.findProductById(product.getId());

        if(product1!=null){
            product.setModifiedAt(new Date());
            return productRepository.save(product);
        }else {
            return new Product();
        }


    }
    @DeleteMapping (path = "/deleteProduct/{id}")
    public boolean DeleteProduct(@PathVariable Long id){

        Product product = productRepository.findProductById(id);

        if(product!=null){
            productRepository.deleteById(id);
            return true;
        }else {
            return false;
        }


    }

    /* ------------------------------- Category --------------------------------------- */

    @GetMapping(path = "/categories")
    public Collection<Category> getCategories(){
        return categoryRepository.findAll();

    }
    @GetMapping(path = "/categories/{id}")
    public Category getCategoryByID(@PathVariable("id")Long id){
        return categoryRepository.findCategoryById(id);

    }

    @PostMapping(path = "/addCategory")
    public Category addCategory(@RequestBody Category category) throws CategoryExistException {

        Category category1=categoryRepository.findCategoryByNom(category.getNom());

        if(category1!=null)
            throw  new CategoryExistException("category already exist") ;

        category.setCreatedAt(new Date());
        return categoryRepository.save(category);

    }
    @PostMapping (path = "/updateCategory")
    public Category updateCategory(@RequestBody Category category){

        Category category1=categoryRepository.findCategoryById(category.getId());

        if(category1!=null){
            category.setModifiedAt(new Date());
            return categoryRepository.save(category);
        }else {
            return new Category();
        }


    }
    @DeleteMapping (path = "/deleteCategory/{id}")
    public boolean DeleteCategory(@PathVariable Long id){

        Category category = categoryRepository.findCategoryById(id);

        if(category!=null){
            categoryRepository.deleteById(id);
            return true;
        }else {
            return false;
        }


    }
    // find products by nom category
  @GetMapping(path = "/productsByCategory/{nom}")
  public Collection<Product> getProductsByCategory(@PathVariable("nom")String nom) {
      if (nom.equals("all")) {
          return productRepository.findAll();
      } else {

          return productRepository.findProductByCategoryNom("%"+nom+"%");

      }

  }

  // get all rules
    @GetMapping(path = "/rules")
    public Collection<Rules> getRules(){
        return rulesRepository.findAll();

    }

    // add rule
    @GetMapping(path = "/addRule")
    public Rules addRule(@RequestBody String path)  {

        String pathEncoded = URLEncoder.encode(path, StandardCharsets.UTF_8);
        String url = "http://127.0.0.1:8000/run_apriori/"+pathEncoded;
        ResponseEntity<Rules> response = restTemplate.getForEntity(url, Rules.class);
        Rules rules = response.getBody();
        // Do something with the response
        if (rules != null) return rulesRepository.save(rules);
        return new Rules();

    }

    // search category by nom
    @GetMapping(path = "/searchCategory/{nom}")
    public Collection<Category> searchCategory(@PathVariable("nom")String nom) {
        return categoryRepository.findCategoryByName("%"+nom+"%");

    }



    // convert product to csv file
    @GetMapping(path = "/convertToCSV")
    public List<DaoProduct> convertToCSV() throws IOException {

        List<Product> allProduct = this.productRepository.findAll();

         List<DaoProduct> daoProducts =mapper(allProduct);


        CsvMapper csvMapper = new CsvMapper();
       /* CsvSchema csvSchema = csvMapper.schemaFor(Product.class).withHeader();
        File csvFile = new File("C:\\Users\\hp\\Desktop\\products.csv");
        csvMapper.writer(csvSchema).writeValue(csvFile, allProduct);*/

       CsvSchema csvSchema = csvMapper.schemaFor(DaoProduct.class)
               .withHeader()
               .withColumnSeparator(',')
               .withLineSeparator("\n");
        UUID uuid = UUID.randomUUID();
        File csvFile = new File("C:\\Users\\hp\\Desktop\\"+"products"+uuid+".csv");
        csvMapper.writer(csvSchema).writeValue(csvFile, daoProducts);
            return  daoProducts;
    }


    public List<DaoProduct> mapper(List<Product> products){

        List<DaoProduct> daoProducts = new ArrayList<>();
        for (Product product : products) {
           daoProducts.add(new DaoProduct(product.getId(), product.getLabel()));
        }
        return daoProducts;
    }






}
