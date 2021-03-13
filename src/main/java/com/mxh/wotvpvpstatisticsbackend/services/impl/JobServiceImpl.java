package com.mxh.wotvpvpstatisticsbackend.services.impl;

import com.mxh.wotvpvpstatisticsbackend.dtos.JobResponseDTO;
import com.mxh.wotvpvpstatisticsbackend.exceptions.ObjectNotFoundException;
import com.mxh.wotvpvpstatisticsbackend.models.CharacterJob;
import com.mxh.wotvpvpstatisticsbackend.repositories.CharacterJobRepository;
import com.mxh.wotvpvpstatisticsbackend.repositories.JobRepository;
import com.mxh.wotvpvpstatisticsbackend.services.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    JobRepository jobRepository;
    CharacterJobRepository characterJobRepository;

    JobServiceImpl(JobRepository jobRepository, CharacterJobRepository characterJobRepository){
        this.jobRepository = jobRepository;
        this.characterJobRepository = characterJobRepository;
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

    @Override
    public List<JobResponseDTO> findByCharacterId(Long id) {
        List<JobResponseDTO> jobs = new ArrayList<>();
        List<CharacterJob> characterJobs = characterJobRepository.findByCharacterId(id);
        characterJobs.forEach(characterJob -> {
            var dto = new JobResponseDTO();
            dto.setId(characterJob.getJob().getId());
            dto.setDescription(characterJob.getJob().getDescription());
            dto.setImage(characterJob.getJob().getImage());
            jobs.add(dto);
        });
        return jobs;
    }
}
