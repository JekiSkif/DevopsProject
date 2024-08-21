package com.example.demo.controller;

import com.example.demo.dto.CICDJobCreateDTO;
import com.example.demo.dto.CICDJobUpdateDTO;
import com.example.demo.model.CICDJob;
import com.example.demo.service.CICDJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class CICDJobController {

    // Logger instance for logging information and debugging
    private static final Logger logger = LoggerFactory.getLogger(CICDJobController.class);

    // Injecting CICDJobService to handle business logic related to CICD jobs
    @Autowired
    private CICDJobService cicdJobService;

    // Endpoint to fetch all CICD jobs
    @GetMapping
    public List<CICDJob> getAllJobs() {
        logger.info("Fetching all jobs");
        return cicdJobService.getAllJobs(); // Delegates to service to retrieve all jobs
    }

    // Endpoint to create a new CICD job
    @PostMapping
    public CICDJob createJob(@RequestBody CICDJobCreateDTO cicdJobCreateDTO) {
        logger.info("Creating new job: {}", cicdJobCreateDTO.getJobName());
        CICDJob cicdJob = new CICDJob();
        cicdJob.setJobName(cicdJobCreateDTO.getJobName());
        cicdJob.setStatus(cicdJobCreateDTO.getStatus());
        cicdJob.setJobType(cicdJobCreateDTO.getJobType());
        return cicdJobService.createJob(cicdJob); // Delegates to service to create a new job
    }

    // Endpoint to fetch a specific CICD job by its ID
    @GetMapping("/{id}")
    public CICDJob getJobById(@PathVariable Long id) {
        logger.info("Fetching job with id: {}", id);
        return cicdJobService.getJobById(id)
                .orElseThrow(() -> new RuntimeException("Job not found")); // Throws exception if job not found
    }

    // Endpoint to update an existing CICD job by its ID
    @PutMapping("/{id}")
    public CICDJob updateJob(@PathVariable Long id, @RequestBody CICDJobUpdateDTO cicdJobUpdateDTO) {
        logger.info("Updating job with id: {}", id);
        CICDJob cicdJob = new CICDJob();
        cicdJob.setJobName(cicdJobUpdateDTO.getJobName());
        cicdJob.setStatus(cicdJobUpdateDTO.getStatus());
        cicdJob.setJobType(cicdJobUpdateDTO.getJobType());
        return cicdJobService.updateJob(id, cicdJob); // Delegates to service to update the job
    }

    // Endpoint to delete a specific CICD job by its ID
    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable Long id) {
        logger.info("Deleting job with id: {}", id);
        cicdJobService.deleteJob(id); // Delegates to service to delete the job
    }

    // Endpoint to fetch jobs by their status
    @GetMapping("/status/{status}")
    public List<CICDJob> getJobsByStatus(@PathVariable String status) {
        logger.info("Fetching jobs with status: {}", status);
        return cicdJobService.getJobsByStatus(status); // Delegates to service to retrieve jobs by status
    }

    // Endpoint to fetch jobs by their job type
    @GetMapping("/jobType/{jobType}")
    public List<CICDJob> getJobsByJobType(@PathVariable String jobType) {
        logger.info("Fetching jobs with job type: {}", jobType);
        return cicdJobService.getJobsByJobType(jobType); // Delegates to service to retrieve jobs by job type
    }

    // Endpoint to fetch jobs within a specific date range
    // used with chatGPT
    @GetMapping("/date-range")
    public List<CICDJob> getJobsByDateRange(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
        logger.info("Fetching jobs between {} and {}", start, end);
        return cicdJobService.getJobsByDateRange(start, end); // Delegates to service to retrieve jobs by date range
    }
}
