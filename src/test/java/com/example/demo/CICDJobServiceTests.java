package com.example.demo;

import com.example.demo.model.CICDJob;
import com.example.demo.repository.CICDJobRepository;
import com.example.demo.service.CICDJobService;
import org.junit.jupiter.api.Test; // Provides support for writing tests in JUnit 5.
import org.junit.jupiter.api.extension.ExtendWith; // Allows extending test functionality with additional features.
import org.mockito.InjectMocks; // Injects mock objects into the class under test.
import org.mockito.Mock; // Creates mock objects.
import org.mockito.junit.jupiter.MockitoExtension; // Integrates Mockito with JUnit 5.

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*; // Provides various assertion methods.
import static org.mockito.Mockito.*; // Provides Mockito methods for mocking.

@ExtendWith(MockitoExtension.class) // Tells JUnit 5 to use MockitoExtension to handle Mockito annotations.
public class CICDJobServiceTests {

    @Mock // Creates a mock instance of CICDJobRepository.
    private CICDJobRepository cicdJobRepository;

    @InjectMocks // Injects the mock repository into the CICDJobService instance.
    private CICDJobService cicdJobService;

    @Test // Marks this method as a test method.
    public void testAddJob() {
        // Arrange: Set up a job object with a job name.
        CICDJob job = new CICDJob();
        job.setJobName("Test Job");

        // Mock the behavior of the repository to return the job object when save is called.
        when(cicdJobRepository.save(any(CICDJob.class))).thenReturn(job);

        // Act: Call the createJob method in the service.
        CICDJob createdJob = cicdJobService.createJob(job);

        // Assert: Verify that the job was created successfully and the repository save method was called.
        assertNotNull(createdJob); // Asserts that the created job is not null.
        assertEquals("Test Job", createdJob.getJobName()); // Asserts that the job name is as expected.
        verify(cicdJobRepository, times(1)).save(any(CICDJob.class)); // Verifies that save method was called exactly once.
    }

    @Test // Marks this method as a test method.
    public void testGetJob() {
        // Arrange: Set up a job object with an ID and a job name.
        CICDJob job = new CICDJob();
        job.setId(1L);
        job.setJobName("Test Job");

        // Mock the behavior of the repository to return the job object when findById is called with ID 1L.
        when(cicdJobRepository.findById(1L)).thenReturn(Optional.of(job));

        // Act: Call the getJobById method in the service.
        Optional<CICDJob> foundJob = cicdJobService.getJobById(1L);

        // Assert: Verify that the job was found and the repository findById method was called.
        assertTrue(foundJob.isPresent()); // Asserts that a job was found.
        assertEquals("Test Job", foundJob.get().getJobName()); // Asserts that the job name matches the expected value.
        verify(cicdJobRepository, times(1)).findById(1L); // Verifies that findById method was called exactly once with ID 1L.
    }
}
