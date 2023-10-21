package com.example.AUIlab.Services;

import com.example.AUIlab.EntityClasses.Job;
import com.example.AUIlab.EntityClasses.Person;
import com.example.AUIlab.Repositories.JobRepository;
import com.example.AUIlab.Repositories.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    private final JobRepository jobRepository;

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository, JobRepository jobRepository){
        this.jobRepository = jobRepository;
        this.personRepository = personRepository;
    }

    public Optional<Person> find(UUID id){
        return personRepository.findById(id);
    }

    public List<Person> findAll(){
        return personRepository.findAll();
    }

    public Optional<Person> findByNameAndId(String name, UUID id){
        return personRepository.findByNameAndId(name, id);
    }

    public List<Person> findAllBySalary(int salary){
        return personRepository.findAllBySalary(salary);
    }

    public Optional<List<Person>> findAllByJob(UUID jobId){
        return jobRepository.findById(jobId).map(personRepository::findAllByJob);
    }

    public void create(Person person){
        personRepository.save(person);
    }

    public void update(Person person){
        personRepository.save(person);
    }

    public void delete(UUID Id){
        personRepository.findById(Id).ifPresent(personRepository::delete);
    }
}
