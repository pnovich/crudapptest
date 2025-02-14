package com.example.crudapptest.controller;

import com.example.crudapptest.entity.Person;
import com.example.crudapptest.entity.Purchase;
import com.example.crudapptest.service.PersonService;
import com.example.crudapptest.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class PersonController {
    private static final Logger log = Logger.getLogger(PersonController.class.getName());
    @Autowired
    PersonService personService;

    @Autowired
    PurchaseService purchaseService;

    @GetMapping("/persons")
    public ResponseEntity<List<Person>> getAllPersons() {
        log.info("retrieving persons");
        try {
            List<Person> persons = personService.getAllPersons();
            return new ResponseEntity<>(persons, HttpStatus.OK);
        } catch (Exception e) {
            log.info("error on server side" + e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/persons")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        log.info("creating new person");
        try {
            Person createdPerson = personService.createPerson(person);
            return new ResponseEntity<>(createdPerson, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("persons/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        log.info("deleting new person");
        try {
            personService.deletePerson(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("persons/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable(required = false) Long id,
                               @RequestBody Person person) {
         log.info("updating person");
         try {
             Person updatedPerson = personService.updatePerson(person);
             return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
         } catch (Exception e) {
             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
         }
    }

    @PatchMapping("persons/cancel/{id}")
    public ResponseEntity<HttpStatus> cancelAllPurchasesForPerson(@PathVariable Long id) {
        //not implmented
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("persons/deletebyname/{name}")
    public ResponseEntity<HttpStatus> deleteAllPersonsWithSomeName(@PathVariable String name) {
        log.info("deleting all persons");
        try {
            personService.deletePersonsListByName(name);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/persons/random")
    public ResponseEntity<Person> createRandomePerson() {
        log.info("creating random person");
        try {
            Person person = new Person("e",25,50,null);
            return new ResponseEntity<>(personService.createPerson(person), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
