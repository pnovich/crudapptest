package com.example.crudapptest.controller;

import com.example.crudapptest.entity.Person;
import com.example.crudapptest.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AppTestController {
    @Autowired
    PersonService personService;

    @GetMapping("/")
    public String defaultString() {
        return "app works, this is default string";
    }

    @GetMapping("/test")
    public String testUrl() {
        return "this is test url";
    }

    @GetMapping("/person/findall")
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
}
