package org.vaadin.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vaadin.example.backend.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
