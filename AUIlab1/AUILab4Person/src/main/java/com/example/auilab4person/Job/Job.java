package com.example.auilab4person.Job;

import com.example.auilab4person.Person.Person;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "jobs")
public class Job implements Serializable {
    @Id
    private UUID id;
    private String name;

    @OneToMany(mappedBy = "job", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Person> persons;
}
