//package com.klef.jfsd.handloom_products_backend.controller;
//
//import com.klef.jfsd.handloom_products_backend.model.Product;
//import com.klef.jfsd.handloom_products_backend.service.ProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.util.Base64;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/products")
//public class ProductController {
//
//    @Autowired
//    private ProductService productService;
//
//    @PostMapping
//    public ResponseEntity<?> addProduct(@RequestParam("name") String name,
//                                        @RequestParam("description") String description,
//                                        @RequestParam("price") BigDecimal price,
//                                        @RequestParam("stock") int stock,
//                                        @RequestParam("category") String category,
//                                        @RequestParam(value = "image", required = false) MultipartFile file,  // Optional image
//                                        @RequestParam("sellerId") int sellerId) {
//        try {
//            // Validate input fields
//            if (name == null || name.trim().isEmpty()) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product name is required.");
//            }
//            if (description == null || description.trim().isEmpty()) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product description is required.");
//            }
//            if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product price must be greater than zero.");
//            }
//            if (stock < 0) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Stock cannot be negative.");
//            }
//            if (category == null || category.trim().isEmpty()) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product category is required.");
//            }
//
//            // If an image is provided, validate its size
//            byte[] imageBytes = null;
//            if (file != null && !file.isEmpty()) {
//                if (file.getSize() > 5000000) {  // 5MB limit
//                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                            .body("File size is too large. Maximum allowed size is 5MB.");
//                }
//
//                // Convert the image to a byte array
//                imageBytes = file.getBytes();
//            } else {
//                // Handle case for no image provided (optional default image or null)
//                imageBytes = null;  // Or set a default image
//            }
//
//            // Create a new product with the byte array image
//            Product product = new Product(name, description, price, stock, category, imageBytes, sellerId);
//
//            // Save the product using the product service
//            Product savedProduct = productService.addProduct(product);
//            if (savedProduct == null) {
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                        .body("Failed to save the product. Please try again.");
//            }
//
//            return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error while uploading the image: " + e.getMessage());
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("An unexpected error occurred: " + e.getMessage());
//        }
//    }
//
//
////    // Get all products with Base64 encoded images
////    @GetMapping
////    public List<Product> getAllProducts() {
////        List<Product> products = productService.getAllProducts();
////        // Convert images to Base64 if not already converted
////        for (Product product : products) {
////            if (product.getImageBase64() == null && product.getImage() != null) {
////                String encodedImage = Base64.getEncoder().encodeToString(product.getImage());
////                product.setImageBase64(encodedImage); // Set the Base64 string
////            }
////        }
////        return products;
////    }
////
////    // Get a product by ID with Base64 image encoding
////    @GetMapping("/{id}")
////    public Product getProductById(@PathVariable int id) {
////        Product product = productService.getProductById(id);
////        
////        // Convert image to Base64 if not already set
////        if (product != null && product.getImageBase64() == null && product.getImage() != null) {
////            String encodedImage = Base64.getEncoder().encodeToString(product.getImage());
////            product.setImageBase64(encodedImage); // Set Base64 image string
////        }
////        
////        return product;
////    }
//    @GetMapping
//    public List<Product> getAllProducts() {
//        // Get all products, with imageBase64 updated by the service
//        return productService.getAllProducts();
//    }
//
//    @GetMapping("/{id}")
//    public Product getProductById(@PathVariable int id) {
//        // Get the product, with imageBase64 updated by the service
//        return productService.getProductById(id);
//    }
//
//
////    @PutMapping("/{id}")
////    public ResponseEntity<Product> updateProduct(@PathVariable("id") int id,
////                                                 @RequestParam("name") String name,
////                                                 @RequestParam("description") String description,
////                                                 @RequestParam("price") BigDecimal price,
////                                                 @RequestParam("stock") int stock,
////                                                 @RequestParam("category") String category,
////                                                 @RequestParam(value = "imageBase64", required = false) String imageBase64) {
////        Product product = productService.getProductById(id);
////        if (product == null) {
////            return ResponseEntity.notFound().build();
////        }
////
////        product.setName(name);
////        product.setDescription(description);
////        product.setPrice(price);
////        product.setStock(stock);
////        product.setCategory(category);
////
////        // If imageBase64 is provided, decode and set the image
////        if (imageBase64 != null && !imageBase64.isEmpty()) {
////            byte[] decodedImage = Base64.getDecoder().decode(imageBase64); // Decode the Base64 string
////            product.setImage(decodedImage);  // Store the decoded byte array
////        }
////
////        // Save the updated product
////        productService.updateProduct(product);
////
////        return ResponseEntity.ok(product);  // Return the updated product
////    }
//    
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Product> updateProduct(@PathVariable("id") int id,
//                                                 @RequestParam("name") String name,
//                                                 @RequestParam("description") String description,
//                                                 @RequestParam("price") BigDecimal price,
//                                                 @RequestParam("stock") int stock,
//                                                 @RequestParam("category") String category,
//                                                 @RequestParam(value = "imageBase64", required = false) String imageBase64) {
//        // Fetch the product by ID
//        Product product = productService.getProductById(id);
//        if (product == null) {
//            return ResponseEntity.notFound().build(); // Return 404 if product not found
//        }
//
//        // Update product details
//        product.setName(name);
//        product.setDescription(description);
//        product.setPrice(price);
//        product.setStock(stock);
//        product.setCategory(category);
//
//        // If imageBase64 is provided, decode and set the image
//        if (imageBase64 != null && !imageBase64.isEmpty()) {
//            try {
//                byte[] decodedImage = Base64.getDecoder().decode(imageBase64); // Decode Base64 image
//                product.setImage(decodedImage);  // Save the decoded image as a byte array
//            } catch (IllegalArgumentException e) {
//                return ResponseEntity.badRequest().body(null); // Return 400 for invalid Base64 string
//            }
//        }
//
//        // Update product in the database
//        productService.updateProduct(product);
//
//        return ResponseEntity.ok(product);  // Return the updated product
//    }
//
//    // Delete a product by ID
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
//        boolean isDeleted = productService.deleteProduct(id);
//        if (isDeleted) {
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//    }
//}
