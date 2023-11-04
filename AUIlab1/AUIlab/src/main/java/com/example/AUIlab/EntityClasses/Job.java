package com.example.AUIlab.EntityClasses;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

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
    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Person> people;

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
        return name.equals(prof.getName()) && Objects.equals(workHoursPerDay, prof.getWorkHoursPerDay());
    }

    @Override
    public int hashCode(){
        return Objects.hash(name,workHoursPerDay);
    }
}
