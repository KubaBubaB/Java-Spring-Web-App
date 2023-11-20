package com.example.auilab4person.Person.function;

import com.example.auilab4person.Person.Person;
import com.example.auilab4person.Person.dto.PatchPersonRequest;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
public class UpdatePersonWithRequestFunction implements BiFunction<Person, PatchPersonRequest, Person> {
    @Override
    public Person apply(Person person, PatchPersonRequest patchPersonRequest) {
        return Person.builder().id(person.getId())
                .name(patchPersonRequest.getName() == null ? person.getName() : patchPersonRequest.getName())
                .salary(patchPersonRequest.getSalary() == null ? person.getSalary() : patchPersonRequest.getSalary())
                .build();
    }
}
