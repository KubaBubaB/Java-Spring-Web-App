package com.example.AUIlab.Services;


import com.example.AUIlab.EntityClasses.Job;
import com.example.AUIlab.Repositories.JobRepository;
import com.example.AUIlab.Repositories.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JobService {
    private final JobRepository jobRepository;

    private final PersonRepository personRepository;

    public JobService(JobRepository jobRepository, PersonRepository personRepository){
        this.jobRepository = jobRepository;
        this.personRepository = personRepository;
    }

    public Optional<Job> find(UUID id) {
        return jobRepository.findById(id);
    }

    public Optional<Job> find(Job job, UUID id) {
        return jobRepository.findByIdAndJob(id, job);
    }

    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    public List<Job> findAll(Job job) {
        return jobRepository.findAllByJob(job);
    }

    public List<Job>findAllByWorkHoursPerDay(int workHoursPerDay){
        return jobRepository.findAllByWorkHoursPerDay(workHoursPerDay);
    }

    public Optional<List<Job>> findAllByPerson(UUID personId){
        return personRepository.findById(personId).map(jobRepository::findAllByPerson);
    }

    public void create(Job job){
        jobRepository.save(job);
    }

    public void update(Job job){
        jobRepository.save(job);
    }

    public void delete(UUID Id){
        jobRepository.findById(Id).ifPresent(jobRepository::delete);
    }
}
