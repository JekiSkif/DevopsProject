//package com.example.demo;
//
//import com.example.demo.controller.CICDJobController;
//import com.example.demo.model.CICDJob;
//import com.example.demo.repository.CICDJobRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.time.LocalDateTime;
//import java.util.Collections;
//import java.util.Optional;
//
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(CICDJobController.class)
//public class CICDJobControllerIntegrationTests {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private CICDJobRepository cicdJobRepository;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @BeforeEach
//    public void setUp() {
//        // Initialization or setup before each test
//    }
//
//    @Test
//    public void testCreateJob() throws Exception {
//        CICDJob job = new CICDJob();
//        job.setJobName("Integration Test Job");
//        job.setStatus("NEW");
//        job.setJobType("BUILD");
//        job.setCreatedAt(LocalDateTime.now());
//        job.setUpdatedAt(LocalDateTime.now());
//
//        given(cicdJobRepository.save(job)).willReturn(job);
//
//        mockMvc.perform(post("/jobs")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(job)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.jobName").value("Integration Test Job"));
//    }
//
//    @Test
//    public void testGetAllJobs() throws Exception {
//        CICDJob job = new CICDJob();
//        job.setJobName("Integration Test Job");
//        job.setStatus("NEW");
//        job.setJobType("BUILD");
//        job.setCreatedAt(LocalDateTime.now());
//        job.setUpdatedAt(LocalDateTime.now());
//
//        given(cicdJobRepository.findAll()).willReturn(Collections.singletonList(job));
//
//        mockMvc.perform(get("/jobs"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].jobName").value("Integration Test Job"));
//    }
//
//    @Test
//    public void testGetJobById() throws Exception {
//        CICDJob job = new CICDJob();
//        job.setJobName("Integration Test Job");
//        job.setStatus("NEW");
//        job.setJobType("BUILD");
//        job.setCreatedAt(LocalDateTime.now());
//        job.setUpdatedAt(LocalDateTime.now());
//
//        given(cicdJobRepository.findById(1L)).willReturn(Optional.of(job));
//
//        mockMvc.perform(get("/jobs/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.jobName").value("Integration Test Job"));
//    }
//
//    @Test
//    public void testUpdateJob() throws Exception {
//        CICDJob job = new CICDJob();
//        job.setJobName("Updated Job");
//        job.setStatus("UPDATED");
//        job.setJobType("BUILD");
//        job.setCreatedAt(LocalDateTime.now());
//        job.setUpdatedAt(LocalDateTime.now());
//
//        given(cicdJobRepository.findById(1L)).willReturn(Optional.of(job));
//        given(cicdJobRepository.save(job)).willReturn(job);
//
//        mockMvc.perform(put("/jobs/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(job)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.jobName").value("Updated Job"));
//    }
//
//    @Test
//    public void testDeleteJob() throws Exception {
//        mockMvc.perform(delete("/jobs/1"))
//                .andExpect(status().isOk());
//    }
//}
//
