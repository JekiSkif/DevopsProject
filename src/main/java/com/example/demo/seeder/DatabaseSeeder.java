package com.example.demo.seeder;

import com.example.demo.model.CICDJob;
import com.example.demo.repository.CICDJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;

import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration // Marks this class as a configuration class, making it a source of bean definitions.
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired // Injects the CICDJobRepository dependency for interacting with the database.
    private CICDJobRepository cicdJobRepository;

    @Override
    public void run(String... args) throws Exception {
        // Creating sample CICDJob objects with sample data
        CICDJob job1 = new CICDJob(null, "Job1", "SUCCESS", LocalDateTime.now(), LocalDateTime.now(), "BUILD");
        CICDJob job2 = new CICDJob(null, "Job2", "FAILURE", LocalDateTime.now(), LocalDateTime.now(), "DEPLOY");
        CICDJob job3 = new CICDJob(null, "Job3", "SUCCESS", LocalDateTime.now(), LocalDateTime.now(), "DELETE");
        CICDJob job4 = new CICDJob(null, "Job4", "SUCCESS", LocalDateTime.now(), LocalDateTime.now(), "DEPLOY");
        CICDJob job5 = new CICDJob(null, "Job5", "FAILURE", LocalDateTime.now(), LocalDateTime.now(), "BUILD");

        // Saving all the created jobs to the database at once using the repository
        cicdJobRepository.saveAll(Arrays.asList(job1, job2, job3, job4, job5));
    }
}
