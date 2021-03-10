package com.mxh.wotvpvpstatisticsbackend.services;

import com.mxh.wotvpvpstatisticsbackend.dtos.ElementResponseDTO;

import java.util.List;

public interface ElementService {

    ElementResponseDTO findById(Long id);
    List<ElementResponseDTO> findAll();
}
