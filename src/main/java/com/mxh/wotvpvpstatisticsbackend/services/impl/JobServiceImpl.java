package com.mxh.wotvpvpstatisticsbackend.services.impl;

import com.mxh.wotvpvpstatisticsbackend.dtos.JobResponseDTO;
import com.mxh.wotvpvpstatisticsbackend.exceptions.ObjectNotFoundException;
import com.mxh.wotvpvpstatisticsbackend.repositories.JobRepository;
import com.mxh.wotvpvpstatisticsbackend.services.JobService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private JobRepository jobRepository;

    JobServiceImpl(JobRepository jobRepository){
        this.jobRepository = jobRepository;
    }
    @Override
    public JobResponseDTO findById(Long id) {
        var job = jobRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Job não encontrado"));
        var dto = new JobResponseDTO();
        dto.setId(job.getId());
        dto.setDescription(job.getDescription());
        dto.setImage(job.getImage());
        return dto;
    }

    @Override
    public List<JobResponseDTO> findAll() {
        return jobRepository.findAllJobs();
    }
}
