package com.mxh.wotvpvpstatisticsbackend.services;

import com.mxh.wotvpvpstatisticsbackend.dtos.EquipmentResponseDTO;

import java.util.List;

public interface EquipmentService {

    EquipmentResponseDTO findById(Long id);

    List<EquipmentResponseDTO> findAll();
}
