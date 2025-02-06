package com.example.crudapptest.repository;

import com.example.crudapptest.entity.AppHomePage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppHomePageRepository extends JpaRepository<AppHomePage, Long> {
}
