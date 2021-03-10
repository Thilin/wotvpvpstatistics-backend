package com.mxh.wotvpvpstatisticsbackend.repositories;

import com.mxh.wotvpvpstatisticsbackend.dtos.JobResponseDTO;
import com.mxh.wotvpvpstatisticsbackend.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {

    @Query("select new com.mxh.wotvpvpstatisticsbackend.dtos.JobResponseDTO(job.id, job.description, job.image) from Job job")
    List<JobResponseDTO> findAllJobs();
}
