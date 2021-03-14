package com.mxh.wotvpvpstatisticsbackend.services;

import com.mxh.wotvpvpstatisticsbackend.dtos.FormationCreateDTO;
import com.mxh.wotvpvpstatisticsbackend.dtos.FormationResponseDTO;

import java.util.List;

public interface FormationService {

    Long create(FormationCreateDTO dto);
    FormationResponseDTO findById(Long id);
    List<FormationResponseDTO> findByUserId(Long id);

}
