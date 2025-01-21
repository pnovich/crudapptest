package com.example.crudapptest.entity;

import com.example.crudapptest.util.PersonLocation;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="Person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String name;
    int age;
    int money;
    PersonLocation location;
    @OneToMany(mappedBy = "person")
    List<Purchase> purchase;


    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", money=" + money +
                ", location=" + location +
                ", purchase=" + purchase +
                '}';
    }

    public Person() {
    }

    public Person(String name, int age, int money, PersonLocation location) {
        this.name = name;
        this.age = age;
        this.money = money;

        if (location == null) {
            this.location = PersonLocation.UA;
        } else {
            this.location = location;
        }

        this.purchase = new ArrayList<>();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PersonLocation getLocation() {
        return location;
    }

    public void setLocation(PersonLocation location) {
        this.location = location;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public List<Purchase> getPurchase() {
        return purchase;
    }

    public void setPurchase(List<Purchase> purchase) {
        this.purchase = purchase;
    }
}
