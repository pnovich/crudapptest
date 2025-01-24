package com.example.crudapptest.service;

import com.example.crudapptest.entity.Person;
import com.example.crudapptest.entity.Purchase;
import com.example.crudapptest.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Component
public class PurchaseService {
    private static final Logger log = Logger.getLogger(PurchaseService.class.getName());
    @Autowired
    PurchaseRepository purchaseRepository;

    public List<Purchase> getAllPurchases() {
        log.info("getting all purchases");
        return purchaseRepository.findAll();
    }

    public Purchase savePurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    public Purchase updatePurchase(Purchase inputPurchase){
        Long id = inputPurchase.getId();
        Optional<Purchase> optionalPurchase = purchaseRepository.findById(id);
        if (optionalPurchase.isPresent()) {
            Purchase storedPurchase = optionalPurchase.get();
            storedPurchase.setPurchaseName(inputPurchase.getPurchaseName());
            storedPurchase.setCount(inputPurchase.getCount());
            storedPurchase.setPrise(inputPurchase.getPrise());
            return purchaseRepository.save(storedPurchase);
        } else {
            return purchaseRepository.save(inputPurchase);
        }
    }

    public void deletePurchase(Long id) {
        purchaseRepository.deleteById(id);
    }

}
