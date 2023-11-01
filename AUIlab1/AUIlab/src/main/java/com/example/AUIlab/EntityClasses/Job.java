package com.example.AUIlab.EntityClasses;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.*;

@Setter
@Getter
@Builder
@Entity
@Table(name = "jobs")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Job implements Comparable<Job>, Serializable {
    @Id
    private UUID id;
    private String name;
    private Integer workHoursPerDay;
    @OneToMany(mappedBy = "job")
    private List<Person> people;

    public void addPerson(Person chara){
        people.add(chara);
    }

    public void removePerson(Person chara) throws NoSuchElementException{
        if (people.contains(chara)){
            people.remove(chara);
        }
        else{
            throw new NoSuchElementException("Person "+ chara + " was not found.");
        }
    }

    @Override
    public String toString(){
        return "job name: " + name + " Work Hours Per Day: " + workHoursPerDay;
    }

    @Override
    public int compareTo(Job o) {
        if (name.equals(o.getName()) && Objects.equals(workHoursPerDay, o.getWorkHoursPerDay()) && people.equals(o.getPeople())){
            return 0;
        }
        else if (name.equals(o.getName()) && Objects.equals(workHoursPerDay, o.getWorkHoursPerDay())){
            int iterator = 0;
            for (Person chara : people){
                if (o.getPeople().size() <= iterator){
                    return 1;
                }
                if(chara.equals(o.getPeople().get(iterator))){
                    iterator++;
                }
                else{
                    return chara.compareTo(o.getPeople().get(iterator));
                }
            }
            //this should not return 0, if statement above prevents it
            return 0;
        }
        else if (name.equals(o.getName())){
            return Integer.compare(workHoursPerDay, o.getWorkHoursPerDay());
        }
        else{
            return name.compareTo(o.getName());
        }
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        else if (o == null || getClass() != o.getClass()) return false;
        Job prof = (Job) o;
        return name.equals(prof.getName()) && Objects.equals(workHoursPerDay, prof.getWorkHoursPerDay()) && people.equals(prof.getPeople());
    }

    @Override
    public int hashCode(){
        return Objects.hash(name,workHoursPerDay,people);
    }
}
