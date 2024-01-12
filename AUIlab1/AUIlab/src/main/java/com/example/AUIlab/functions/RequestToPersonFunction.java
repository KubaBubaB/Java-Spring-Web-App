package com.example.AUIlab.functions;

import com.example.AUIlab.EntityClasses.Person;
import com.example.AUIlab.dto.PutPersonRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.BiFunction;

@Component
public class RequestToPersonFunction implements BiFunction<UUID, PutPersonRequest, Person> {

    @Override
    public Person apply(UUID uuid, PutPersonRequest putPersonRequest) {
        return Person.builder()
                .id(uuid)
                .name(putPersonRequest.getName())
                .salary(putPersonRequest.getSalary())
                .build();
    }
}
