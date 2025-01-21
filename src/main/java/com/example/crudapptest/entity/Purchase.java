package com.example.crudapptest.entity;

import jakarta.persistence.*;

@Entity
@Table(name="purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    String purchaseName;
    Long count;
    Long prise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="person_id")
    Person person;

    public Purchase() {
    }

    public Purchase(Long count, Long prise, Person person, String purchaseName) {
        this.count = count;
        this.prise = prise;
        this.person = person;
        this.purchaseName = purchaseName;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getPurchaseName() {
        return purchaseName;
    }

    public void setPurchaseName(String purchaseName) {
        this.purchaseName = purchaseName;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getPrise() {
        return prise;
    }

    public void setPrise(Long prise) {
        this.prise = prise;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", purchaseName='" + purchaseName + '\'' +
                ", count=" + count +
                ", prise=" + prise +
                ", person=" + person.getId() +
                '}';
    }
}
