package com.example.auilab4job.repository;

import com.example.auilab4job.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JobRepository extends JpaRepository<Job, UUID> {
    List<Job> findAllByWorkHoursPerDay(int workHoursPerDay);

    Optional<Job> findByIdAndName(UUID Id, String name);

    List<Job> findAllById(UUID id);
}
