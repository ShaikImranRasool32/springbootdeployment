package com.klef.jfsd.handloom_products_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

//import jakarta.persistence.*;
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "Product")
//public class Product {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int productId;
//
//    @Column(nullable = false)
//    private String name;
//
//    @Column(length = 1000)
//    private String description;
//
//    @Column(nullable = false)
//    private BigDecimal price;
//
//    @Column(nullable = false)
//    private int stock;
//
//    private String category;
//
//    @Column(name = "seller_id")
//    private int sellerId;
//
//    @Column(name = "date_added", updatable = false)
//    private LocalDateTime dateAdded;
//
//    // This will store the image as a byte array
//    @Lob
//    @Column(name = "image", columnDefinition = "MEDIUMBLOB")
//    private byte[] image;
//
//    // Default constructor
//    public Product() {
//        this.dateAdded = LocalDateTime.now();
//    }
//
//    public Product(String name, String description, BigDecimal price, int stock, String category, byte[] image, int sellerId) {
//        this.name = name;
//        this.description = description;
//        this.price = price;
//        this.stock = stock;
//        this.category = category;
//        this.image = image;  // Storing image as byte array
//        this.sellerId = sellerId;
//    }
//
//    // Getters and Setters
//    public int getProductId() {
//        return productId;
//    }
//
//    public void setProductId(int productId) {
//        this.productId = productId;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public BigDecimal getPrice() {
//        return price;
//    }
//
//    public void setPrice(BigDecimal price) {
//        this.price = price;
//    }
//
//    public int getStock() {
//        return stock;
//    }
//
//    public void setStock(int stock) {
//        this.stock = stock;
//    }
//
//    public String getCategory() {
//        return category;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }
//
//    public int getSellerId() {
//        return sellerId;
//    }
//
//    public void setSellerId(int sellerId) {
//        this.sellerId = sellerId;
//    }
//
//    public LocalDateTime getDateAdded() {
//        return dateAdded;
//    }
//
//    public void setDateAdded(LocalDateTime dateAdded) {
//        this.dateAdded = dateAdded;
//    }
//
//    public byte[] getImage() {
//        return image;
//    }
//
//    public void setImage(byte[] image) {
//        this.image = image;
//    }
//}


@Entity
public class Product {

    @Id
    private Long id;
    private String itemName;
    private double itemPrice;
    private String filePath;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
