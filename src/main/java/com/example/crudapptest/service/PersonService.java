package com.example.crudapptest.service;

import com.example.crudapptest.entity.Person;
import com.example.crudapptest.repository.PersonRepository;
import com.example.crudapptest.util.PersonLocation;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class PersonService {
    @Autowired
    PersonRepository personRepository;

    @PostConstruct
    private void initDb() {
      Person person1 = new Person("p1",20, 200,null);
      Person person2 = new Person("p2",30, 300, null);
      Person person3 = new Person("p3",50,3000, PersonLocation.USA);
      Person testPerson = new Person("test", 30,10000,PersonLocation.India);
      List<Person> list = Arrays.asList(person1, person2, person3, testPerson);
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
        return personRepository.findAll();
    }

    public List<Person> saveAllPersons(List<Person> personList) {
        return personRepository.saveAll(personList);
    }


}
