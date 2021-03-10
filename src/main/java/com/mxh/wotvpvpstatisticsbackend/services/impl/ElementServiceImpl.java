package com.mxh.wotvpvpstatisticsbackend.services.impl;

import com.mxh.wotvpvpstatisticsbackend.dtos.ElementResponseDTO;
import com.mxh.wotvpvpstatisticsbackend.exceptions.ObjectNotFoundException;
import com.mxh.wotvpvpstatisticsbackend.repositories.ElementRepository;
import com.mxh.wotvpvpstatisticsbackend.services.ElementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElementServiceImpl implements ElementService {

    private ElementRepository elementRepository;

    public ElementServiceImpl(ElementRepository elementRepository){
        this.elementRepository = elementRepository;
    }

    @Override
    public ElementResponseDTO findById(Long id) {
        var element = elementRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Elemento não encontrado"));
        var dto = new ElementResponseDTO();
        dto.setId(element.getId());
        dto.setDescription(element.getDescription());
        dto.setImage(element.getImage());
        return dto;
    }

    @Override
    public List<ElementResponseDTO> findAll() {
        return elementRepository.findAllElements();
    }
}
