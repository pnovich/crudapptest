package com.example.crudapptest.service;

import com.example.crudapptest.entity.Person;
import com.example.crudapptest.entity.Purchase;
import com.example.crudapptest.repository.PersonRepository;
import com.example.crudapptest.util.PersonLocation;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Component
public class PersonService {
    private static final Logger log = Logger.getLogger(PersonService.class.getName());
    @Autowired
    PersonRepository personRepository;

    @PostConstruct
    private void initDb() {
      Person person1 = new Person("p1",20, 200,null);
      Person person2 = new Person("p2",30, 300, null);
      Person person3 = new Person("p3",50,3000, PersonLocation.USA);
      Person testPerson = new Person("test", 30,10000,PersonLocation.India);
      List<Person> list = Arrays.asList(person1, person2, person3, testPerson);
      log.info("init mthod, saving prsons, count is " + list.size());
      saveAllPersons(list);
    }

    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    public Person updatePerson(Person inputPerson) {
        Long inputId = inputPerson.getId();
        Optional<Person> optionalPerson = personRepository.findById(inputId);
        if (optionalPerson.isPresent()) {
            Person storedPerson = optionalPerson.get();
            storedPerson.setAge(inputPerson.getAge());
            storedPerson.setName(inputPerson.getName());
            storedPerson.setMoney(inputPerson.getMoney());
            storedPerson.setLocation(inputPerson.getLocation() == null ? PersonLocation.UA : inputPerson.getLocation());
            personRepository.save(storedPerson);
            return storedPerson;
        } else {
            return personRepository.save(inputPerson);
        }

    }

    public Person updatePersonById(
            Long id, String name, int age,int money, PersonLocation location) {
        Person person = new Person(name, age, money, location);
        person.setId(id);
        return updatePerson(person);
    }

    public void deletePersonsListByName(String name) {
        List<Person> personsWithSameName = findPersonsByName(name);
        personRepository.deleteAll(personsWithSameName);
    }

    public  void deleteAllPersons() {
        personRepository.deleteAll();
    }

    public List<Person> findPersonsByName(String name) {
        return personRepository.findPersonByName(name);
    }

    public Optional<Person> getPersonById(Long id) {
        return personRepository.findById(id);
    }

    public List<Person> getAllPersons() {
        log.info("getting all persons");
        return personRepository.findAll();
    }

    public List<Person> saveAllPersons(List<Person> personList) {
        return personRepository.saveAll(personList);
    }

    public void addPurchaseToPerson(Purchase purchase) {
        Long personId = purchase.getPerson().getId();
        Optional<Person> optionalPerson = getPersonById(personId);
        if (optionalPerson.isPresent()) {
            Person personFromPurchase = optionalPerson.get();
            personFromPurchase.getPurchases().add(purchase);
        } else {
            throw new RuntimeException("Person is not present");
        }
    }


}
