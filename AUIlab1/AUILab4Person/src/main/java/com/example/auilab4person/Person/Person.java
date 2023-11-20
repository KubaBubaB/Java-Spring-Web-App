package com.example.auilab4person.Person;

import com.example.auilab4person.Job.Job;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "persons")
public class Person {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "job")
    private Job job;
    private String name;
    private Integer salary;

}
