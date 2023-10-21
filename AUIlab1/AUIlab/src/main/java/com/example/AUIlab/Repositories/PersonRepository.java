package com.example.AUIlab.Repositories;

import com.example.AUIlab.EntityClasses.Job;
import com.example.AUIlab.EntityClasses.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {
    Optional<Person> findByNameAndId(String name, UUID id);
    List<Person> findAllByJob(Job job);

    List<Person> findAllBySalary(int salary);
}
