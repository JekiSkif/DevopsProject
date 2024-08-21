//The purpose of a service in Spring Boot is to contain the business logic of the application.
// The service layer sits between the controller (which handles HTTP requests) and the repository (which interacts with the database).
// It processes data, applies business rules, and orchestrates calls to the repository for data persistence and retrieval.

package com.example.demo.service;

import com.example.demo.model.CICDJob;
import com.example.demo.repository.CICDJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service // Marks this class as a Spring service, making it a candidate for Spring's component scanning to detect and register it as a bean.
public class CICDJobService {

    @Autowired // Injects the CICDJobRepository dependency to interact with the database.
    private CICDJobRepository cicdJobRepository;

    // Retrieves all CICD jobs from the database
    public List<CICDJob> getAllJobs() {
        return cicdJobRepository.findAll(); // Uses the repository to fetch all jobs
    }

    // Creates a new CICD job and saves it to the database
    public CICDJob createJob(CICDJob cicdJob) {
        cicdJob.setCreatedAt(LocalDateTime.now()); // Sets the current timestamp as the creation time
        cicdJob.setUpdatedAt(LocalDateTime.now()); // Sets the current timestamp as the last updated time
        return cicdJobRepository.save(cicdJob); // Saves the job to the database
    }

    // Retrieves a specific CICD job by its ID
    public Optional<CICDJob> getJobById(Long id) {
        return cicdJobRepository.findById(id); // Uses the repository to find a job by its ID, returning an Optional
    }

    // Updates an existing CICD job with new details
    public CICDJob updateJob(Long id, CICDJob cicdJob) {
        return cicdJobRepository.findById(id).map(existingJob -> {
            existingJob.setJobName(cicdJob.getJobName()); // Updates the job name
            existingJob.setStatus(cicdJob.getStatus()); // Updates the job status
            existingJob.setJobType(cicdJob.getJobType()); // Updates the job type
            existingJob.setUpdatedAt(LocalDateTime.now()); // Updates the last modified timestamp
            return cicdJobRepository.save(existingJob); // Saves the updated job to the database
        }).orElseThrow(() -> new RuntimeException("Job not found")); // Throws an exception if the job is not found
    }

    // Deletes a CICD job by its ID
    public void deleteJob(Long id) {
        cicdJobRepository.deleteById(id); // Uses the repository to delete the job by its ID
    }

    // Retrieves CICD jobs by their status
    public List<CICDJob> getJobsByStatus(String status) {
        return cicdJobRepository.findByStatus(status); // Uses the repository to find jobs by their status
    }

    // Retrieves CICD jobs by their job type
    public List<CICDJob> getJobsByJobType(String jobType) {
        return cicdJobRepository.findByJobType(jobType); // Uses the repository to find jobs by their job type
    }

    // Retrieves CICD jobs within a specific date range
    public List<CICDJob> getJobsByDateRange(LocalDateTime start, LocalDateTime end) {
        return cicdJobRepository.findByCreatedAtBetween(start, end); // Uses the repository to find jobs within the specified date range
    }
}
