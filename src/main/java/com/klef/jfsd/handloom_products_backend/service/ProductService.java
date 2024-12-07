//package com.klef.jfsd.handloom_products_backend.service;
//
//import com.klef.jfsd.handloom_products_backend.model.Product;
//import com.klef.jfsd.handloom_products_backend.repository.ProductRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.StandardCopyOption;
//import java.util.List;
//import java.util.UUID;
//
//@Service
//public class ProductService {
//    @Autowired
//    private ProductRepository productRepository;
//
//    public Product addProduct(Product product) {
//        return productRepository.save(product);
//    }
//
//    public List<Product> getAllProducts() {
//        return productRepository.findAll();
//    }
//
//    public Product getProductById(int id) {
//        return productRepository.findById(id).orElse(null);
//    }
//
//    public Product updateProduct(Product product) {
//        return productRepository.save(product);
//    }
//
//    public void deleteProduct(int id) {
//        productRepository.deleteById(id);
//    }
//   
//}
package com.klef.jfsd.handloom_products_backend.service;

import com.klef.jfsd.handloom_products_backend.model.Product;
import com.klef.jfsd.handloom_products_backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    
    // Add a new product
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get a product by ID
    public Product getProductById(int id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);  // Return null if product is not found
    }

    // Update an existing product
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    // Delete a product by ID
    public boolean deleteProduct(int id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}