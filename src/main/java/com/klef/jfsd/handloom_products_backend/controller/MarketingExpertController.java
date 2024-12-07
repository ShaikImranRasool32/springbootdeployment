package com.klef.jfsd.handloom_products_backend.controller;

import com.klef.jfsd.handloom_products_backend.model.MarketingExpert;
import com.klef.jfsd.handloom_products_backend.service.MarketingExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/marketingexperts")
public class MarketingExpertController {

    @Autowired
    private MarketingExpertService marketingExpertService;

    @PostMapping("/signup")
    public MarketingExpert registerMarketingExpert(@RequestBody MarketingExpert marketingExpert) {
        return marketingExpertService.registerMarketingExpert(marketingExpert);
    }

    // Updated login method
    @PostMapping("/login")
    public MarketingExpert loginMarketingExpert(@RequestBody MarketingExpert marketingExpert) {
        return marketingExpertService.loginMarketingExpert(marketingExpert.getEmail(), marketingExpert.getPassword());
    }

    @GetMapping("/{id}")
    public MarketingExpert getMarketingExpertById(@PathVariable Long id) {
        return marketingExpertService.getMarketingExpertById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public MarketingExpert updateMarketingExpert(@PathVariable Long id, @RequestBody MarketingExpert updatedMarketingExpert) {
        return marketingExpertService.updateMarketingExpert(id, updatedMarketingExpert);
    }
}
