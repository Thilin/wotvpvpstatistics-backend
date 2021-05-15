package com.mxh.wotvpvpstatisticsbackend.services;

import com.mxh.wotvpvpstatisticsbackend.dtos.ConfrontationDTO;
import com.mxh.wotvpvpstatisticsbackend.dtos.ConfrontationReportDTO;
import com.mxh.wotvpvpstatisticsbackend.dtos.ConfrontationTeamsDTO;

import java.util.List;

public interface ConfrontationService {
    Long register(ConfrontationDTO dto);

    List<ConfrontationTeamsDTO> findAll();

    List<ConfrontationReportDTO> findAllBySeasonId(Long seasonId);
}
