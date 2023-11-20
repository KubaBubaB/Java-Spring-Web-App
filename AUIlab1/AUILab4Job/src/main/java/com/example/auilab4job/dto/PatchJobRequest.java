package com.example.auilab4job.dto;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PatchJobRequest {
    private String name;
    private Integer workingHoursPerDay;
}
