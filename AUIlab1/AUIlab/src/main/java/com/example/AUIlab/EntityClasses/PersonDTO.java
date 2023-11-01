package com.example.AUIlab.EntityClasses;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonDTO implements Comparable<PersonDTO>{
    private String name;
    private Integer salary;
    private String job;

    @Override
    public int compareTo(PersonDTO o) {
        if(o.getName().equals(name) && Objects.equals(o.salary, salary) && o.getJob().equals(job)){
            return 0;
        }
        else if (name.equals(o.getName()) && Objects.equals(o.salary, salary)){
            return job.compareTo(o.getJob());
        }
        else if (name.equals(o.getName())){
            return Integer.compare(salary,o.salary);
        }
        else{
            return name.compareTo(o.getName());
        }
    }
}
