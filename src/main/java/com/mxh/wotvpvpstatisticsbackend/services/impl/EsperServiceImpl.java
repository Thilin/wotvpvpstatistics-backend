package com.mxh.wotvpvpstatisticsbackend.services.impl;

import com.mxh.wotvpvpstatisticsbackend.dtos.EsperResponseDTO;
import com.mxh.wotvpvpstatisticsbackend.exceptions.ObjectNotFoundException;
import com.mxh.wotvpvpstatisticsbackend.repositories.EsperRepository;
import com.mxh.wotvpvpstatisticsbackend.services.EsperService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EsperServiceImpl implements EsperService {

    private EsperRepository esperRepository;

    EsperServiceImpl(EsperRepository esperRepository){
        this.esperRepository = esperRepository;
    }

    @Override
    public EsperResponseDTO findById(Long id) {
        var esper = esperRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Esper Not Found"));
        var dto = new EsperResponseDTO();
        dto.setId(esper.getId());
        dto.setName(esper.getName());
        dto.setImage(esper.getImage());
        dto.setRarity(esper.getRarity());
        dto.setElement(esper.getElement().getDescription());
        return dto;
    }

    @Override
    public List<EsperResponseDTO> findAll() {
        return esperRepository.findAllEspers();
    }
}
