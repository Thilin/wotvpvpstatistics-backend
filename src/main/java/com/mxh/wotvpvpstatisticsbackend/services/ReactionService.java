package com.mxh.wotvpvpstatisticsbackend.services;

import com.mxh.wotvpvpstatisticsbackend.dtos.ReactionResponseDTO;

import java.util.List;

public interface ReactionService {

    ReactionResponseDTO findById(Long id);
    List<ReactionResponseDTO> findAll();
}
