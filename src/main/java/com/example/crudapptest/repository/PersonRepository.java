package com.example.crudapptest.repository;

import com.example.crudapptest.entity.Person;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findPersonByName(String name);
//    @EntityGraph(attributePaths = "purchases")
    @Query(value = "SELECT u FROM Person u left join fetch u.purchases")
    List<Person> findAll();
}
