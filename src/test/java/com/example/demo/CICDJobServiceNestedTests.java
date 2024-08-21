package com.example.demo;

import com.example.demo.model.CICDJob;
import com.example.demo.repository.CICDJobRepository;
import com.example.demo.service.CICDJobService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Integrates Mockito with JUnit 5 for this test class.
public class CICDJobServiceNestedTests {

    @Mock // Creates a mock instance of CICDJobRepository to simulate database operations.
    private CICDJobRepository cicdJobRepository;

    @InjectMocks // Injects the mock repository into the CICDJobService to create a testable service instance.
    private CICDJobService cicdJobService;

    @Nested // Groups related test cases into a nested class for better organization.
    class CreateJobTests {

        @Test // Marks this method as a test case for creating a job.
        public void testCreateJob() {
            // Arrange: Set up the job object and mock repository behavior.
            CICDJob job = new CICDJob();
            job.setJobName("Test Job");

            // Mock the behavior of the repository to return the job object when save is called.
            when(cicdJobRepository.save(any(CICDJob.class))).thenReturn(job);

            // Act: Call the createJob method in the service.
            CICDJob createdJob = cicdJobService.createJob(job);

            // Assert: Verify that the job was created successfully and that the correct methods were called.
            assertNotNull(createdJob); // Asserts that the created job is not null.
            assertEquals("Test Job", createdJob.getJobName()); // Asserts that the job name is correct.
            verify(cicdJobRepository, times(1)).save(any(CICDJob.class)); // Verifies that the save method was called exactly once.
        }
    }

    @Nested // Another nested class to group test cases related to reading (retrieving) jobs.
    class ReadJobTests {

        @Test // Marks this method as a test case for retrieving a job by ID.
        public void testGetJobById() {
            // Arrange: Set up the job object and mock repository behavior.
            CICDJob job = new CICDJob();
            job.setId(1L);
            job.setJobName("Test Job");

            // Mock the behavior of the repository to return the job when findById is called with ID 1L.
            when(cicdJobRepository.findById(1L)).thenReturn(Optional.of(job));

            // Act: Call the getJobById method in the service.
            Optional<CICDJob> foundJob = cicdJobService.getJobById(1L);

            // Assert: Verify that the job was found and the details are correct.
            assertTrue(foundJob.isPresent()); // Asserts that a job was found.
            assertEquals("Test Job", foundJob.get().getJobName()); // Asserts that the job name is correct.
            verify(cicdJobRepository, times(1)).findById(1L); // Verifies that the findById method was called exactly once.
        }
    }
}
