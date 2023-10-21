package com.example.AUIlab.cmd;

import com.example.AUIlab.EntityClasses.Job;
import com.example.AUIlab.Services.JobService;
import com.example.AUIlab.Services.PersonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

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
                }
                case "delete_person" ->{
                    //delete person by uuid
                }
                case "list_all_people" ->{
                    //list all people
                }
                case "list_all_jobs" ->{
                    //list jobs
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
