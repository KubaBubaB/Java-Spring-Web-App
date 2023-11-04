package com.example.AUIlab.functions;

import com.example.AUIlab.EntityClasses.Job;
import com.example.AUIlab.dto.PutJobRequest;
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
