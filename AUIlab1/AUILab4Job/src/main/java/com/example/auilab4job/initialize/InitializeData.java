package com.example.auilab4job.initialize;

import com.example.auilab4job.entity.Job;
import com.example.auilab4job.service.JobService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class InitializeData implements InitializingBean {

        private final JobService jobService;

    public InitializeData(JobService jobService) {
        this.jobService = jobService;
    }

    @Override
        public void afterPropertiesSet() throws Exception {
            if(jobService.findAll().isEmpty()){
                Job waiter = Job.builder().id(UUID.fromString("a63676a0-0008-4168-bfeb-bcdeaa6976de")).
                    name("waiter").workHoursPerDay(8).build();
                Job bartender = Job.builder().id(UUID.fromString("3e34770b-1cf0-429b-a901-ca97f43d0ff9")).
                    name("bartender").workHoursPerDay(12).build();
                Job cook = Job.builder().id(UUID.fromString("5b67dea4-3648-4d5a-a121-a470b72355f8")).
                    name("cook").workHoursPerDay(16).build();
                Job dishwasher = Job.builder().id(UUID.fromString("a4631ac7-6a79-4131-a852-9a19a6188cc9")).
                    name("dishwasher").workHoursPerDay(16).build();

                jobService.create(waiter);
                jobService.create(bartender);
                jobService.create(cook);
                jobService.create(dishwasher);
            }
        }
}
