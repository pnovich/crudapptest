package com.example.crudapptest.controller;

import com.example.crudapptest.entity.Person;
import com.example.crudapptest.entity.Purchase;
import com.example.crudapptest.service.PersonService;
import com.example.crudapptest.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.management.RuntimeErrorException;
import java.util.List;
import java.util.Optional;

@RestController
public class AppTestController {
    @Autowired
    PersonService personService;

    @Autowired
    PurchaseService purchaseService;

    @GetMapping("/")
    public String defaultString() {
        return "app works, this is default string";
    }

    @GetMapping("/test")
    public String testUrl() {
        return "this is test url";
    }

    @GetMapping("/person/all")
    public List<Person> gtAllPersons() {
      return personService.getAllPersons();
    }

    @GetMapping("/person/random")
    public Person createRandomePerson() {
        Person person = new Person("e",25,50,null);
        return personService.createPerson(person);
    }

    @GetMapping("person/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        System.out.println("inside delete method");
        personService.deletePerson(id);
        System.out.println("after deleting");
    }

    @GetMapping("person/deletebyname/{name}")
    public void deleteAllPersonsWithSomeName(@PathVariable String name) {
        personService.deletePersonsListByName(name);
    }

    @GetMapping("purchase/all")
    public List<Purchase> findAllPurchases() {
        return purchaseService.getAllPurchases();
    }

    @GetMapping("purchase/test/{id}")
    public String createTestPurchase(
            @PathVariable Long id
    ) {
        Optional<Person> optionalPerson = personService.getPersonById(id);
        if (optionalPerson.isPresent()) {
            Person testPerson = optionalPerson.get();
            Long count = 3l;
            Long price = 156l;
            Purchase test = new Purchase(count,price, testPerson, "some present");
            return purchaseService.addPurchaseToPerson(test).toString();
        } else {
            throw new RuntimeException("Person is not present");
        }
    }
}
