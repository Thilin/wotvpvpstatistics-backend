package com.mxh.wotvpvpstatisticsbackend.services;

import com.mxh.wotvpvpstatisticsbackend.dtos.VisionCardResponseDTO;

import java.util.List;

public interface VisionCardService {

    VisionCardResponseDTO findById(Long id);
    List<VisionCardResponseDTO> findAll();
}
