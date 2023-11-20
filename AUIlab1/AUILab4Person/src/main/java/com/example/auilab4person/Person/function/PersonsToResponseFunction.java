package com.example.auilab4person.Person.function;

import com.example.auilab4person.Person.Person;
import com.example.auilab4person.Person.dto.GetPersonsResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class PersonsToResponseFunction implements Function<List<Person>, GetPersonsResponse> {
    @Override
    public GetPersonsResponse apply(List<Person> persons) {
        return GetPersonsResponse.builder()
                .persons(persons.stream()
                        .map(person -> GetPersonsResponse.Person.builder()
                                .id(person.getId())
                                .name(person.getName())
                                .build())
                        .toList())
                .build();
    }
}
