package com.klef.jfsd.handloom_products_backend.service;

import com.klef.jfsd.handloom_products_backend.model.MarketingExpert;
import com.klef.jfsd.handloom_products_backend.repository.MarketingExpertRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarketingExpertService {

    @Autowired
    private MarketingExpertRepository marketingExpertRepository;

    public MarketingExpert registerMarketingExpert(MarketingExpert marketingExpert) {
        return marketingExpertRepository.save(marketingExpert);
    }

    public MarketingExpert loginMarketingExpert(String email, String password) {
        MarketingExpert marketingExpert = marketingExpertRepository.findByEmail(email);
        if (marketingExpert != null && marketingExpert.getPassword().equals(password)) {
            return marketingExpert;  // Return the marketingExpert object if credentials match
        }
        return null;  // Return null if no matching marketing expert found or password is incorrect
    }

    public Optional<MarketingExpert> getMarketingExpertById(Long id) {
        return marketingExpertRepository.findById(id);
    }

    public MarketingExpert updateMarketingExpert(Long id, MarketingExpert updatedMarketingExpert) {
        Optional<MarketingExpert> marketingExpertOpt = marketingExpertRepository.findById(id);
        if (marketingExpertOpt.isPresent()) {
            MarketingExpert marketingExpert = marketingExpertOpt.get();
            marketingExpert.setEmail(updatedMarketingExpert.getEmail());
            marketingExpert.setPassword(updatedMarketingExpert.getPassword());
            return marketingExpertRepository.save(marketingExpert);
        }
        return null;
    }
}
