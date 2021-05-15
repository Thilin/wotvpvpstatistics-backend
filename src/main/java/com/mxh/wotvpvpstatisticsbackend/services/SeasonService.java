package com.mxh.wotvpvpstatisticsbackend.services;

import com.mxh.wotvpvpstatisticsbackend.dtos.SeasonDTO;

import java.util.List;

public interface SeasonService {

    SeasonDTO findById(Long id);
    List<SeasonDTO> listAllByConfrontationTypeId(Long confrontationTypeId);
}
