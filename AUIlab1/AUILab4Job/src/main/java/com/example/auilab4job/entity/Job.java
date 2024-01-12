package com.example.auilab4job.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "job")
public class Job implements Serializable {
    @Id
    private UUID id;
    private String name;
    private Integer workHoursPerDay;
    @Override
    public String toString(){
        return "job name: " + name + " Work Hours Per Day: " + workHoursPerDay;
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
