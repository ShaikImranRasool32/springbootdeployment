//package com.klef.jfsd.handloom_products_backend.model;
//
//import jakarta.persistence.*;
//
//@Entity
//public class Item {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long itemId;
//
//    @Column(nullable = false)
//    private String itemName;
//
//    @Column(nullable = false)
//    private Double itemPrice;
//
//    @Column(nullable = false)
//    private String itemDescription;
//
//    @ManyToOne
//    @JoinColumn(name = "seller_id", referencedColumnName = "sellerId")
//    private Seller seller;
//
//    // Getters and Setters
//    public Long getItemId() {
//        return itemId;
//    }
//
//    public void setItemId(Long itemId) {
//        this.itemId = itemId;
//    }
//
//    public String getItemName() {
//        return itemName;
//    }
//
//    public void setItemName(String itemName) {
//        this.itemName = itemName;
//    }
//
//    public Double getItemPrice() {
//        return itemPrice;
//    }
//
//    public void setItemPrice(Double itemPrice) {
//        this.itemPrice = itemPrice;
//    }
//
//    public String getItemDescription() {
//        return itemDescription;
//    }
//
//    public void setItemDescription(String itemDescription) {
//        this.itemDescription = itemDescription;
//    }
//
//    public Seller getSeller() {
//        return seller;
//    }
//
//    public void setSeller(Seller seller) {
//        this.seller = seller;
//    }
//}
