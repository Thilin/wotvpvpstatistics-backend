package com.mxh.wotvpvpstatisticsbackend.services;

import com.mxh.wotvpvpstatisticsbackend.dtos.CharacterResponseDTO;

import java.util.List;

public interface CharacterService {

    CharacterResponseDTO findById(Long id);
    List<CharacterResponseDTO> findAll();
}
