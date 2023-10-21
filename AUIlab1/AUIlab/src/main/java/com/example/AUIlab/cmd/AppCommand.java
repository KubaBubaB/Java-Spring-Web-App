package com.example.AUIlab.cmd;

import com.example.AUIlab.EntityClasses.Job;
import com.example.AUIlab.EntityClasses.Person;
import com.example.AUIlab.Services.JobService;
import com.example.AUIlab.Services.PersonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AppCommand implements CommandLineRunner {
    private JobService jobService;
    private PersonService personService;

    public AppCommand(JobService jobService, PersonService personService){
        this.jobService = jobService;
        this.personService = personService;
    }
    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String command;
        main_loop:
        while(true){
            command = scanner.next();
            switch (command){
                case "add_person" ->{
                    //add a single person
                    System.out.println("UUID of person to be added:");
                    UUID uuidPerson = UUID.fromString(scanner.next());
                    System.out.println("Name of person to be added:");
                    String name = scanner.next();
                    System.out.println("Salary of person to be added:");
                    int salary;
                    try{
                        salary = scanner.nextInt();
                    }
                    catch(InputMismatchException ex){
                        System.out.println("Not an integer");
                        continue;
                    }

                    System.out.println("Job UUID of person to be added:");
                    UUID uuidJob = UUID.fromString(scanner.next());
                    try {
                        personService.create(Person.builder().
                                id(uuidPerson).name(name).salary(salary).
                                job(jobService.find(uuidJob).orElseThrow()).build());
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Bad Request");
                    }

                }
                case "delete_person" ->{
                    //delete person by uuid
                    System.out.println("UUID of person to be deleted:");
                    try {
                        UUID uuid = UUID.fromString(scanner.next());
                        personService.delete(uuid);
                    } catch (NoSuchElementException ex) {
                        System.out.println("Not Found");
                    }


                }
                case "list_all_people" ->{
                    //list all people
                    personService.findAll().forEach(System.out::println);
                }
                case "list_all_jobs" ->{
                    //list jobs
                    jobService.findAll().forEach(System.out::println);
                }
                case "help" ->{
                    System.out.println("""
                            add_person: adds a person\s
                            delete_person: deletes a person provided a UUID\s
                            list_all_people: lists all people\s
                            list all jobs: lists all jobs\s
                            help: lists all commands\s
                            exit: exits app""");
                }
                case "exit" ->{
                    //exit app
                    break main_loop;
                }
                default -> {
                    System.out.println("Command not recognized! Type \"help\" to list all commands :)");
                }
            }
        }
    }
}
