package com.mxh.wotvpvpstatisticsbackend.services.impl;

import com.mxh.wotvpvpstatisticsbackend.dtos.SeasonDTO;
import com.mxh.wotvpvpstatisticsbackend.exceptions.ObjectNotFoundException;
import com.mxh.wotvpvpstatisticsbackend.models.Season;
import com.mxh.wotvpvpstatisticsbackend.repositories.SeasonRepository;
import com.mxh.wotvpvpstatisticsbackend.services.SeasonService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeasonServiceImpl implements SeasonService {

    public SeasonRepository seasonRepository;

    SeasonServiceImpl(SeasonRepository seasonRepository){
        this.seasonRepository=seasonRepository;
    }
    @Override
    public SeasonDTO findById(Long id) {
        var dto = new SeasonDTO();
        var season = seasonRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Temporada não encontrada"));
        dto.setCode(season.getCode());
        dto.setMap(season.getMap().getName());
        dto.setId(season.getId());
        dto.setType(season.getConfrontationType().getDescription());
        dto.setStart(season.getDateStart());
        return dto;
    }

    @Override
    public List<SeasonDTO> listAllByConfrontationTypeId(Long confrontationTypeId) {
        List<SeasonDTO> seasonsDTOS = new ArrayList<>();
        List<Season> seasons = seasonRepository.findByConfrontationTypeId(confrontationTypeId);
        seasons.forEach(season -> {
            var dto = new SeasonDTO();
            dto.setCode(season.getCode());
            dto.setMap(season.getMap().getName());
            dto.setId(season.getId());
            dto.setType(season.getConfrontationType().getDescription());
            dto.setStart(season.getDateStart());
            seasonsDTOS.add(dto);
        });

        return seasonsDTOS;
    }
}
