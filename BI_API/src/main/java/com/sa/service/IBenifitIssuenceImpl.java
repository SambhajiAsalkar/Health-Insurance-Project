package com.sa.service;

import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IBenifitIssuenceImpl implements IBenifitIssuence{

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @Override
    public JobExecution sendBenificiaryAmt() throws Exception {
        // Use java.util.Date instead of java.sql.Date
        JobParameter<Long> param = new JobParameter<>(System.currentTimeMillis(), Long.class);
        
        Map<String, JobParameter<?>> map = Map.of(
            "executionTime", param  // Better parameter name
        );
        
        JobParameters params = new JobParameters(map);
        JobExecution execution = jobLauncher.run(job, params);
        System.out.println("Job Status: " + execution.getStatus());
        return execution;
    }

	
}
