// The purpose of a repository in Spring Boot is to provide a layer of abstraction over the data access logic.
// Repositories handle the interaction with the database, allowing you to perform CRUD (Create, Read, Update, Delete) operations
// and custom queries without writing boilerplate code.

package com.example.demo.repository;

import com.example.demo.model.CICDJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository // Indicates that this interface is a Spring Data repository, responsible for CRUD operations on CICDJob entities.
public interface CICDJobRepository extends JpaRepository<CICDJob, Long> {

    // Method to find all CICD jobs with a specific status
    List<CICDJob> findByStatus(String status);

    // Method to find all CICD jobs with a specific job type
    List<CICDJob> findByJobType(String jobType);

    // Method to find all CICD jobs created within a specific date range
    List<CICDJob> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}
