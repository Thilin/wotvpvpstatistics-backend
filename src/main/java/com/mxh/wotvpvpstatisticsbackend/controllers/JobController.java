package com.mxh.wotvpvpstatisticsbackend.controllers;

import com.mxh.wotvpvpstatisticsbackend.dtos.JobResponseDTO;
import com.mxh.wotvpvpstatisticsbackend.services.JobService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/jobs")
public class JobController {

    JobService jobService;

    JobController(JobService jobService){
        this.jobService = jobService;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "Find a job by id", description = "Find a job using its id")
    public ResponseEntity<JobResponseDTO> findById(@PathVariable Long id){
        var dto = jobService.findById(id);
        if(dto == null){
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok().body(dto);
        }
    }

    @GetMapping(value = "/all", produces = "application/json")
    @Operation(summary = "show all jobs", description = "Show all jobs informations")
    public ResponseEntity<List<JobResponseDTO>> findAll(){
        return ResponseEntity.ok().body(jobService.findAll());
    }
}
