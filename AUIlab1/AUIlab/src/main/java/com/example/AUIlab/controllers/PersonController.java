package com.example.AUIlab.controllers;

import com.example.AUIlab.Services.PersonService;
import com.example.AUIlab.dto.GetPersonResponse;
import com.example.AUIlab.dto.GetPersonsResponse;
import com.example.AUIlab.dto.PatchPersonRequest;
import com.example.AUIlab.dto.PutPersonRequest;
import com.example.AUIlab.functions.PersonToResponseFunction;
import com.example.AUIlab.functions.PersonsToResponseFunction;
import com.example.AUIlab.functions.RequestToPersonFunction;
import com.example.AUIlab.functions.UpdatePersonWithRequestFunction;
import lombok.*;
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

   private final PersonToResponseFunction personToResponseFunction;

   private final PersonsToResponseFunction personsToResponseFunction;

    private final RequestToPersonFunction requestToPersonFunction;

    private final UpdatePersonWithRequestFunction updatePersonWithRequestFunction;

    @Autowired
    public PersonController(PersonService personService, PersonToResponseFunction personToResponseFunction, PersonsToResponseFunction personsToResponseFunction, RequestToPersonFunction requestToPersonFunction, UpdatePersonWithRequestFunction updatePersonWithRequestFunction) {
        this.personService = personService;
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
        if(putPersonRequest.getJobsId() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
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
