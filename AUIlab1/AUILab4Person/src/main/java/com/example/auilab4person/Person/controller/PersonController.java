package com.example.auilab4person.Person.controller;

import com.example.auilab4person.Job.JobService;
import com.example.auilab4person.Person.dto.GetPersonResponse;
import com.example.auilab4person.Person.dto.GetPersonsResponse;
import com.example.auilab4person.Person.dto.PatchPersonRequest;
import com.example.auilab4person.Person.dto.PutPersonRequest;
import com.example.auilab4person.Person.function.PersonToResponseFunction;
import com.example.auilab4person.Person.function.PersonsToResponseFunction;
import com.example.auilab4person.Person.function.RequestToPersonFunction;
import com.example.auilab4person.Person.function.UpdatePersonWithRequestFunction;
import com.example.auilab4person.Person.service.PersonService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@Log
@RequestMapping("/work")
public class PersonController {
    private final PersonService personService;

    private final JobService jobService;

    private final PersonToResponseFunction personToResponseFunction;

    private final PersonsToResponseFunction personsToResponseFunction;

    private final RequestToPersonFunction requestToPersonFunction;

    private final UpdatePersonWithRequestFunction updatePersonWithRequestFunction;

    @Autowired
    public PersonController(PersonService personService, JobService jobService, PersonToResponseFunction personToResponseFunction, PersonsToResponseFunction personsToResponseFunction, RequestToPersonFunction requestToPersonFunction, UpdatePersonWithRequestFunction updatePersonWithRequestFunction) {
        this.personService = personService;
        this.jobService = jobService;
        this.personToResponseFunction = personToResponseFunction;
        this.personsToResponseFunction = personsToResponseFunction;
        this.requestToPersonFunction = requestToPersonFunction;
        this.updatePersonWithRequestFunction = updatePersonWithRequestFunction;
    }

    @GetMapping("/persons")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public GetPersonsResponse getPersons(){
        return personsToResponseFunction.apply(personService.findAll());
    }

    @GetMapping("/persons/jobs/{jobsId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public GetPersonsResponse getJobPersons(@PathVariable UUID jobsId){
        return personService.findAllByJob(jobsId)
                .map(personsToResponseFunction)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/persons/salary/{salary}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public GetPersonsResponse getSalaryPersons(@PathVariable int salary){
        return personsToResponseFunction.apply(personService.findAllBySalary(salary));
    }

    @GetMapping("/persons/{personId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public GetPersonResponse getPerson(@PathVariable UUID personId){
        return personService.find(personId)
                .map(personToResponseFunction)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/persons/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPerson(@PathVariable UUID id,@RequestBody PutPersonRequest putPersonRequest){
        if(jobService.find(putPersonRequest.getJobsId()).isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if(personService.find(id).isPresent()){
            personService.delete(id);
            personService.create(requestToPersonFunction.apply(id, putPersonRequest));
            throw new ResponseStatusException(HttpStatus.CREATED);
        }
        personService.create(requestToPersonFunction.apply(id, putPersonRequest));
    }

    @PatchMapping("/persons/{personId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updatePerson(@PathVariable UUID personId, @RequestBody PatchPersonRequest patchPersonRequest){
        personService.find(personId)
                .map(person -> updatePersonWithRequestFunction.apply(person, patchPersonRequest))
                .ifPresentOrElse(personService::update, () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                });
    }

    @DeleteMapping("/persons/{personId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable UUID personId){
        personService.find(personId)
                .ifPresentOrElse(character -> personService.delete(personId), () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                });
    }
}
