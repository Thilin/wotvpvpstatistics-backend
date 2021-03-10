package com.mxh.wotvpvpstatisticsbackend.services;

import com.mxh.wotvpvpstatisticsbackend.dtos.EsperResponseDTO;

import java.util.List;

public interface EsperService {

    EsperResponseDTO findById(Long id);
    List<EsperResponseDTO> findAll();
}
