package com.example.auilab4job.service;

import com.example.auilab4job.entity.Job;
import com.example.auilab4job.event.JobEventRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.auilab4job.repository.JobRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JobService {
    private int counter = 0;
    private final JobRepository jobRepository;
    private final JobEventRestRepository jobEventRestRepository;

    @Autowired
    public JobService(JobRepository jobRepository, JobEventRestRepository jobEventRestRepository) {
        this.jobRepository = jobRepository;
        this.jobEventRestRepository = jobEventRestRepository;
    }
    public Optional<Job> find(UUID id) {
        return jobRepository.findById(id);
    }

    public Optional<Job> find(String name, UUID id) {
        return jobRepository.findByIdAndName(id, name);
    }

    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    public List<Job> findAll(UUID id) {
        return jobRepository.findAllById(id);
    }

    public List<Job>findAllByWorkHoursPerDay(int workHoursPerDay){
        return jobRepository.findAllByWorkHoursPerDay(workHoursPerDay);
    }

    public void create(Job job){
        System.out.println("creating job: " + job.getName());
        jobRepository.save(job);
        if(counter >= 4){
            jobEventRestRepository.create(job.getId(), job.getName());
        }
        counter++;
    }

    public void update(Job job){
        //Job myJob = jobRepository.findById(job.getId()).orElseThrow();
        //myJob.setName(job.getName());
        //myJob.setWorkHoursPerDay(job.getWorkHoursPerDay());
        //myJob.setPeople(job.getPeople());
        //jobRepository.save(myJob);
        jobRepository.save(job);
        jobEventRestRepository.create(job.getId(), job.getName());
    }

    public void delete(UUID Id){
        jobRepository.findById(Id).ifPresent(jobRepository::delete);
        jobEventRestRepository.delete(Id);
    }
}
