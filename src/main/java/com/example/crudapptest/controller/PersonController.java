package com.example.crudapptest.controller;

import com.example.crudapptest.entity.Person;
import com.example.crudapptest.entity.Purchase;
import com.example.crudapptest.service.PersonService;
import com.example.crudapptest.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

import java.util.List;
import java.util.Optional;

@RestController

public class PersonController {
    private static final Logger log = Logger.getLogger(PersonController.class.getName());
    @Autowired
    PersonService personService;

    @Autowired
    PurchaseService purchaseService;

    @GetMapping("/persons")
    public List<Person> gtAllPersons() {
        log.info("retrieving persons");
      return personService.getAllPersons();
    }

    @PostMapping("/persons")
    public Person createPerson(@RequestBody Person person) {
        log.info("creating new person");
        return personService.createPerson(person);
    }


    @DeleteMapping("persons/{id}")
    public void deleteById(@PathVariable Long id) {
        log.info("deleting new person");
        personService.deletePerson(id);
    }

    @PutMapping("persons/{id}")
    public Person updatePerson(@PathVariable(required = false) Long id,
                               @RequestBody Person person) {
         log.info("updating person");
         return personService.updatePerson(person);
    }

    @PatchMapping("persons/cancel/{id}")
    public void cancelAllPurchasesForPerson(@PathVariable Long id) {
        //mot implmented
    }

    @DeleteMapping("persons/deletebyname/{name}")
    public void deleteAllPersonsWithSomeName(@PathVariable String name) {
        personService.deletePersonsListByName(name);
    }

    @PostMapping("/persons/random")
    public Person createRandomePerson() {
        Person person = new Person("e",25,50,null);
        return personService.createPerson(person);
    }

}
