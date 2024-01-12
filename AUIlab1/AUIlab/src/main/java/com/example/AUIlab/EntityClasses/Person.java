package com.example.AUIlab.EntityClasses;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.lang.String;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "people")
public class Person implements Comparable<Person>, Serializable {
    @Id
    private UUID id;
    private String name;
    private Integer salary;
    @ManyToOne
    @JoinColumn(name = "job")
    private Job job;

    @Override
    public String toString(){
        return "Person: " + name + ", Salary: " + salary + ", job: " + job.getName()+job.getWorkHoursPerDay();
    }

    @Override
    public int compareTo(Person o) {
        if(o.getName().equals(name) && Objects.equals(o.salary, salary) && o.getJob().getName().equals(job.getName())){
            return 0;
        }
        else if (name.equals(o.getName()) && Objects.equals(o.salary, salary)){
            return job.getName().compareTo(o.getJob().getName());
        }
        else if (name.equals(o.getName())){
            return Integer.compare(salary,o.salary);
        }
        else{
            return name.compareTo(o.getName());
        }
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        else if (o == null || getClass() != o.getClass()) return false;
        Person chara = (Person) o;
        return Objects.equals(salary, chara.getSalary()) && name.equals(chara.getName()) &&
                job.getName().equals(chara.getJob().getName()) &&
                Objects.equals(job.getWorkHoursPerDay(), chara.getJob().getWorkHoursPerDay());
    }

    @Override
    public int hashCode(){
        return Objects.hash(name, salary, job.getName(), job.getWorkHoursPerDay());
    }
}