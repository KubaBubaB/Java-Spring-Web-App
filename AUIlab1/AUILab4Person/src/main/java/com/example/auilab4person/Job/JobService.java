package com.example.auilab4person.Job;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class JobService {
    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Optional<Job> find(UUID id) {
        return jobRepository.findById(id);
    }

    public void create(Job job) {
        if(jobRepository.findAll().contains(job)){
            jobRepository.findById(job.getId()).get().setName(job.getName());
            return;
        }
        jobRepository.save(job);
    }

    public void delete(UUID id) {
        jobRepository.findById(id).ifPresent(jobRepository::delete);
    }
}
