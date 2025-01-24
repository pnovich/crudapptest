package com.example.crudapptest.controller;

import com.example.crudapptest.entity.Person;
import com.example.crudapptest.entity.Purchase;
import com.example.crudapptest.service.PersonService;
import com.example.crudapptest.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
public class PurchaseController {
    private static final Logger log = Logger.getLogger(PurchaseController.class.getName());
    @Autowired
    PurchaseService purchaseService;
    @Autowired
    PersonService personService;

    @GetMapping("purchase/all")
    public List<Purchase> findAllPurchases() {
        log.info("retrieving purchases");
        return purchaseService.getAllPurchases();
    }

    @PostMapping("purchase/create")
    public Purchase ctreatePurchase(@RequestBody Purchase purchase) {
        log.info("creating purchase");
        return purchaseService.savePurchase(purchase);
    }

    @PutMapping("purchase/update/{id}")
    public Purchase updatePurchase(@PathVariable(required = false) Long id,
                                   @RequestBody Purchase purchase) {
        log.info("updating purchase");
        return purchaseService.updatePurchase(purchase);

    }

    @DeleteMapping("purchase/delete/{id}")
    public void deletePurchase(@PathVariable Long id) {
       log.info("deleting purchase");
       purchaseService.deletePurchase(id);
    }

    @GetMapping("purchase/test/{id}")
    public Purchase createTestPurchase(
            @PathVariable Long id
    ) {
        Optional<Person> optionalPerson = personService.getPersonById(id);
        if (optionalPerson.isPresent()) {
            Person testPerson = optionalPerson.get();
            Long count = 3l;
            Long price = 156l;
            Purchase test = new Purchase(count,price, testPerson, "some present");
            personService.addPurchaseToPerson(test);
            return purchaseService.savePurchase(test);
        } else {
            throw new RuntimeException("Person is not present");
        }
    }

}
