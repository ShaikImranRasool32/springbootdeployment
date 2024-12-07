//package com.klef.jfsd.handloom_products_backend.model;
//
//import jakarta.persistence.*;
//
//@Entity
//public class Seller {
//    
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String name;
//    private String email;
//    private String password;
//    private String phoneNumber;  // Use camel case consistently
//    private String address;
//    private String CompanyName;
//    private String role = "SELLER"; // Default role
//
//    // Getters and setters
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//
//    public String getName() { return name; }
//    public void setName(String name) { this.name = name; }
//
//    public String getEmail() { return email; }
//    public void setEmail(String email) { this.email = email; }
//
//    public String getPassword() { return password; }
//    public void setPassword(String password) { this.password = password; }
//
//    public String getRole()
//    { return role; }
//    public void setRole(String role)
//    { this.role = role; }
//    
//	
//	public String getAddress() {
//		return address;
//	}
//	public void setAddress(String address) {
//		this.address = address;
//	}
//	public String getCompanyName() {
//		return CompanyName;
//	}
//	public void setCompanyName(String companyName) {
//		CompanyName = companyName;
//	}
//	public String getPhoneNumber() {
//		return phoneNumber;
//	}
//	public void setPhoneNumber(String phoneNumber) {
//		this.phoneNumber = phoneNumber;
//	}
//}
package com.klef.jfsd.handloom_products_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//import jakarta.persistence.*;
//
//@Entity
//public class Seller {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long sellerId;
//
//    @Column(nullable = false, unique = true)
//    private String sellerEmail;
//
//    @Column(nullable = false)
//    private String sellerPassword;
//
//    @Column(nullable = false)
//    private String sellerName;
//
//    private String sellerPhone;
//
//    // Getters and Setters
//    public Long getSellerId() {
//        return sellerId;
//    }
//
//    public void setSellerId(Long sellerId) {
//        this.sellerId = sellerId;
//    }
//
//    public String getSellerEmail() {
//        return sellerEmail;
//    }
//
//    public void setSellerEmail(String sellerEmail) {
//        this.sellerEmail = sellerEmail;
//    }
//
//    public String getSellerPassword() {
//        return sellerPassword;
//    }
//
//    public void setSellerPassword(String sellerPassword) {
//        this.sellerPassword = sellerPassword;
//    }
//
//    public String getSellerName() {
//        return sellerName;
//    }
//
//    public void setSellerName(String sellerName) {
//        this.sellerName = sellerName;
//    }
//
//    public String getSellerPhone() {
//        return sellerPhone;
//    }
//
//    public void setSellerPhone(String sellerPhone) {
//        this.sellerPhone = sellerPhone;
//    }
//}

@Entity
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true, nullable = false)
    private String gmail;

    @Column(nullable = false)
    private String password;
    private String phoneNumber;  // Use camel case consistently
    private String address;
    private String CompanyName;

    public Seller() {
    }

    public Seller(String name, String gmail, String password) {
        this.name = name;
        this.gmail = gmail;
        this.password = password;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCompanyName() {
		return CompanyName;
	}

	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}
}
