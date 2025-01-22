package com.example.crudapptest.service;

import com.example.crudapptest.entity.Person;
import com.example.crudapptest.entity.Purchase;
import com.example.crudapptest.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PurchaseService {
    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    PersonService personService;

    public List<Purchase> getAllPurchases() {
        System.out.println("getting all purchases");
        return purchaseRepository.findAll();
    }

    public Purchase addPurchaseToPerson(Purchase purchase) {
       Long personId = purchase.getPerson().getId();
       Optional<Person> optionalPerson = personService.getPersonById(personId);
       if (optionalPerson.isPresent()) {
           Person personFromPurchase = optionalPerson.get();
           personFromPurchase.getPurchases().add(purchase);
       } else {
           throw new RuntimeException("Person is not present");
       }
    return purchaseRepository.save(purchase);
    }
}
