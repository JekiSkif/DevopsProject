package com.example.demo;

import com.example.demo.model.CICDJob;
import com.example.demo.repository.CICDJobRepository;
import com.example.demo.service.CICDJobService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Enables Mockito for this test class, allowing for the use of @Mock and @InjectMocks.
public class CICDJobServiceExceptionTests {

    @Mock // Mocks the CICDJobRepository to simulate database interactions.
    private CICDJobRepository cicdJobRepository;

    @InjectMocks // Injects the mock repository into the CICDJobService, creating a testable instance with mocked dependencies.
    private CICDJobService cicdJobService;

    @Test // Marks this method as a test case.
    public void testDeleteNonExistentJob() {
        // Mocks the behavior of the repository to throw a RuntimeException when deleteById is called with any Long argument.
        doThrow(new RuntimeException("Job not found")).when(cicdJobRepository).deleteById(anyLong());

        // Asserts that the deleteJob method in the service throws a RuntimeException when called with a non-existent job ID.
        assertThrows(RuntimeException.class, () -> cicdJobService.deleteJob(1L));

        // Verifies that the deleteById method was called exactly once with the argument 1L.
        verify(cicdJobRepository, times(1)).deleteById(1L);
    }

    @Test // Marks this method as a test case.
    public void testUpdateJobWithInvalidData() {
        // Mocks the behavior of the repository to return an empty Optional when findById is called with any Long argument.
        when(cicdJobRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Creates a CICDJob object with invalid data (in this case, just an example job name).
        CICDJob job = new CICDJob();
        job.setJobName("Invalid Job");

        // Asserts that the updateJob method in the service throws a RuntimeException when trying to update a non-existent job.
        assertThrows(RuntimeException.class, () -> cicdJobService.updateJob(1L, job));

        // Verifies that the findById method was called exactly once with the argument 1L.
        verify(cicdJobRepository, times(1)).findById(1L);
    }
}
