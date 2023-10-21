package com.example.AUIlab.EntityClasses;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonDTO implements Comparable<PersonDTO>{
    private String name;
    private int salary;
    private String job;

    @Override
    public int compareTo(PersonDTO o) {
        if(o.getName().equals(name) && o.salary == salary && o.getJob().equals(job)){
            return 0;
        }
        else if (name.equals(o.getName()) && o.salary == salary){
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
