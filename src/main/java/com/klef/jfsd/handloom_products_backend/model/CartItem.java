package com.klef.jfsd.handloom_products_backend.model;

import jakarta.persistence.*;

@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "customer_email") // Optional if you want to specify a custom column name

    private String customerEmail;  // Customer's email
    private String itemName;
    private double itemPrice;
    private String itemSummary;
    private String imagePath;
    private Integer count = 1; // Default quantity is 1
    private Long productId;  // Product ID for identifying the item
    @Column(name = "seller_gmail") // Optional if you want to specify a custom column name
    private String sellerGmail;

    // Getters and setters
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

    public String getItemSummary() {
        return itemSummary;
    }

    public void setItemSummary(String itemSummary) {
        this.itemSummary = itemSummary;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

	

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getSellerGmail() {
		return sellerGmail;
	}

	public void setSellerGmail(String sellerGmail) {
		this.sellerGmail = sellerGmail;
	}

	@Override
	public String toString() {
		return "CartItem [id=" + id + ", customerEmail=" + customerEmail + ", itemName=" + itemName + ", itemPrice="
				+ itemPrice + ", itemSummary=" + itemSummary + ", imagePath=" + imagePath + ", count=" + count
				+ ", productId=" + productId + ", sellerGmail=" + sellerGmail + "]";
	}

    
}
