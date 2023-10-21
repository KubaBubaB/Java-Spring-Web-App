package com.example.AUIlab.Repositories;

import com.example.AUIlab.EntityClasses.Job;
import com.example.AUIlab.EntityClasses.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JobRepository extends JpaRepository<Job, UUID> {
    List<Job> findAllByWorkHoursPerDay(int workHoursPerDay);
    List<Job> findAllByPerson(Person people);

    Optional<Job> findByIdAndJob(UUID Id, Job job);

    List<Job> findAllByJob(Job job);
}
