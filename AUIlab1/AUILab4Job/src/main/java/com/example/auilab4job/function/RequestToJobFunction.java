package com.example.auilab4job.function;


import com.example.auilab4job.dto.PutJobRequest;
import com.example.auilab4job.entity.Job;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.BiFunction;

@Component
public class RequestToJobFunction implements BiFunction<UUID, PutJobRequest, Job> {

    @Override
    public Job apply(UUID uuid, PutJobRequest putJobRequest) {
        return Job.builder()
                .id(uuid)
                .name(putJobRequest.getName())
                .workHoursPerDay(putJobRequest.getWorkingHours())
                .build();
    }
}
