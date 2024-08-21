package com.example.demo;

import com.example.demo.model.CICDJob;
import com.example.demo.repository.CICDJobRepository;
import com.example.demo.service.CICDJobService;
import org.junit.jupiter.params.ParameterizedTest; // Enables parameterized tests.
import org.junit.jupiter.params.provider.CsvSource; // Provides CSV input for parameterized tests.
import org.junit.jupiter.params.provider.ValueSource; // Provides simple values for parameterized tests.
import org.mockito.InjectMocks; // Injects mocks into the test subject.
import org.mockito.Mock; // Creates mock objects.
import org.mockito.junit.jupiter.MockitoExtension; // Integrates Mockito with JUnit 5.
import org.junit.jupiter.api.extension.ExtendWith; // Extends the test class with additional functionality.

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*; // Imports assertions for testing.
import static org.mockito.Mockito.*; // Imports Mockito methods for mocking.

@ExtendWith(MockitoExtension.class) // Integrates Mockito with JUnit 5 for this test class.
public class CICDJobServiceParameterizedTests {

    @Mock // Creates a mock instance of CICDJobRepository to simulate database operations.
    private CICDJobRepository cicdJobRepository;

    @InjectMocks // Injects the mock repository into the CICDJobService to create a testable service instance.
    private CICDJobService cicdJobService;

    @ParameterizedTest // Marks this method as a parameterized test, which runs multiple times with different parameters.
    @CsvSource({ // Provides input data in CSV format for the test.
            "Test Job 1, NEW",
            "Test Job 2, IN_PROGRESS",
            "Test Job 3, COMPLETED"
    })
    public void testAddJobWithVariousStatuses(String jobName, String status) {
        // Arrange: Set up the job object with the given parameters.
        CICDJob job = new CICDJob();
        job.setJobName(jobName);
        job.setStatus(status);

        // Mock the behavior of the repository to return the job object when save is called.
        when(cicdJobRepository.save(any(CICDJob.class))).thenReturn(job);

        // Act: Call the createJob method in the service.
        CICDJob createdJob = cicdJobService.createJob(job);

        // Assert: Verify that the job was created successfully and that the correct methods were called.
        assertNotNull(createdJob); // Asserts that the created job is not null.
        assertEquals(jobName, createdJob.getJobName()); // Asserts that the job name matches the input.
        assertEquals(status, createdJob.getStatus()); // Asserts that the job status matches the input.
        verify(cicdJobRepository, times(1)).save(any(CICDJob.class)); // Verifies that the save method was called exactly once.
    }

    @ParameterizedTest // Marks this method as a parameterized test, which runs multiple times with different parameters.
    @ValueSource(longs = {1L, 2L, 3L}) // Provides input data (long values) for the test.
    public void testGetJobByDifferentIds(Long id) {
        // Arrange: Set up the job object with the given ID.
        CICDJob job = new CICDJob();
        job.setId(id);
        job.setJobName("Test Job");

        // Mock the behavior of the repository to return the job when findById is called with the given ID.
        when(cicdJobRepository.findById(id)).thenReturn(Optional.of(job));

        // Act: Call the getJobById method in the service.
        Optional<CICDJob> foundJob = cicdJobService.getJobById(id);

        // Assert: Verify that the job was found and the details are correct.
        assertTrue(foundJob.isPresent()); // Asserts that a job was found.
        assertEquals(id, foundJob.get().getId()); // Asserts that the job ID matches the input.
        verify(cicdJobRepository, times(1)).findById(id); // Verifies that the findById method was called exactly once.
    }
}
