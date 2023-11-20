package com.example.auilab4person.Person.initialize;

import com.example.auilab4person.Job.Job;
import com.example.auilab4person.Job.JobService;
import com.example.auilab4person.Person.Person;
import com.example.auilab4person.Person.service.PersonService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class InitializeData implements InitializingBean {

    private final JobService jobService;

    private final PersonService personService;

    public InitializeData(JobService jobService, PersonService personService) {
        this.jobService = jobService;
        this.personService = personService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Job waiter = Job.builder().id(UUID.fromString("a63676a0-0008-4168-bfeb-bcdeaa6976de")).
                name("waiter").build();
        Job bartender = Job.builder().id(UUID.fromString("3e34770b-1cf0-429b-a901-ca97f43d0ff9")).
                name("bartender").build();
        Job cook = Job.builder().id(UUID.fromString("5b67dea4-3648-4d5a-a121-a470b72355f8")).
                name("cook").build();
        Job dishwasher = Job.builder().id(UUID.fromString("a4631ac7-6a79-4131-a852-9a19a6188cc9")).
                name("dishwasher").build();

        jobService.create(waiter);
        jobService.create(bartender);
        jobService.create(cook);
        jobService.create(dishwasher);

        Person Alice = Person.builder().id(UUID.fromString("a7392b37-6c9c-43fd-9cc3-9e1c4d069925"))
                .salary(3000).job(waiter).name("Alice").build();

        Person Bob = Person.builder().id(UUID.fromString("d47bf978-5b79-40b6-a056-25f184c88ab8"))
                .salary(3200).job(bartender).name("Bob").build();

        Person Charlie = Person.builder().id(UUID.fromString("f483bebb-00a2-484f-bb1a-9259c0b75530"))
                .salary(3200).job(cook).name("Charlie").build();

        Person David = Person.builder().id(UUID.fromString("c098b7d4-6d4e-42d8-8f9e-258dcf0a77fd"))
                .salary(2800).job(dishwasher).name("David").build();

        Person Eva = Person.builder().id(UUID.fromString("e3672ed6-2a9a-46b6-987c-2b04fb6e4c74"))
                .salary(2900).job(waiter).name("Eva").build();

        Person Frank = Person.builder().id(UUID.fromString("a30ef097-2578-4d4b-a604-1d1b93e697bf"))
                .salary(3300).job(bartender).name("Frank").build();

        Person Grace = Person.builder().id(UUID.fromString("b295c8a0-9b12-434d-96b6-045c571170ac"))
                .salary(3600).job(cook).name("Grace").build();

        Person Henry = Person.builder().id(UUID.fromString("f398ec59-f9e3-4d5e-8c9e-6eafda8cecad"))
                .salary(2700).job(dishwasher).name("Henry").build();

        Person Ivy = Person.builder().id(UUID.fromString("d9611e20-4d9e-4482-a32a-56c8f9f6e5a6"))
                .salary(3100).job(waiter).name("Ivy").build();

        Person Jack = Person.builder().id(UUID.fromString("b44e5c19-5483-4d47-8a64-68e8c0d72703"))
                .salary(3400).job(bartender).name("Jack").build();

        Person Kate = Person.builder().id(UUID.fromString("e7f4fe6f-1e18-4e37-bb57-7b2a7e818a9e"))
                .salary(3700).job(cook).name("Kate").build();

        Person Satish = Person.builder().id(UUID.fromString("f398ec59-f0a5-3a1f-8c9e-6eafda8cecad"))
                .salary(2700).job(dishwasher).name("Satish").build();

        personService.create(Alice);
        personService.create(Satish);
        personService.create(Kate);
        personService.create(Jack);
        personService.create(Ivy);
        personService.create(Henry);
        personService.create(Grace);
        personService.create(Frank);
        personService.create(Eva);
        personService.create(Bob);
        personService.create(David);
        personService.create(Charlie);
    }
}
