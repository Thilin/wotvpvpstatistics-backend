package com.mxh.wotvpvpstatisticsbackend.services;

import com.mxh.wotvpvpstatisticsbackend.dtos.CharacterBuiltCreateDTO;
import com.mxh.wotvpvpstatisticsbackend.dtos.CharacterBuiltResponseDTO;

import java.util.List;

public interface CharacterBuiltService {

    Long create(CharacterBuiltCreateDTO dto);

    CharacterBuiltResponseDTO findById(Long id);

    List<CharacterBuiltResponseDTO> findAllByUserId(Long id);
}
