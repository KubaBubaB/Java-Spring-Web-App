package com.example.AUIlab.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetJobResponse {
    private UUID id;
    private String name;
    private Integer workHoursPerDay;
}
