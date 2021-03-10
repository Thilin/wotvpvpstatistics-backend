package com.mxh.wotvpvpstatisticsbackend.services;

import com.mxh.wotvpvpstatisticsbackend.dtos.JobResponseDTO;

import java.util.List;

public interface JobService {

    JobResponseDTO findById(Long id);
    List<JobResponseDTO> findAll();
}
