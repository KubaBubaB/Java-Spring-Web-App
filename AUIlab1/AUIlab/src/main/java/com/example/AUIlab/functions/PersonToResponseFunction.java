package com.example.AUIlab.functions;

import com.example.AUIlab.EntityClasses.Person;
import com.example.AUIlab.dto.GetPersonResponse;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PersonToResponseFunction implements Function<Person, GetPersonResponse>
{
    @Override
    public GetPersonResponse apply(Person person)
    {
        return GetPersonResponse.builder()
                .id(person.getId())
                .name(person.getName())
                .salary(person.getSalary())
                .job(GetPersonResponse.Job.builder()
                        .id(person.getJob().getId())
                        .name(person.getJob().getName())
                        .build())
                .build();
    }
}
