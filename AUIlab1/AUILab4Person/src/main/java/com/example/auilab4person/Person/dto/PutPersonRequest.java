package com.example.auilab4person.Person.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutPersonRequest {
    private String name;
    private Integer salary;
    private UUID jobsId;
}