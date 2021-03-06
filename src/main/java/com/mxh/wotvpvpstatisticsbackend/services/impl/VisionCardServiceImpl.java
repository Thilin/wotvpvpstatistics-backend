package com.mxh.wotvpvpstatisticsbackend.services.impl;

import com.mxh.wotvpvpstatisticsbackend.dtos.VisionCardResponseDTO;
import com.mxh.wotvpvpstatisticsbackend.exceptions.ObjectNotFoundException;
import com.mxh.wotvpvpstatisticsbackend.mappers.VisionCardMapper;
import com.mxh.wotvpvpstatisticsbackend.repositories.VisionCardRepository;
import com.mxh.wotvpvpstatisticsbackend.services.VisionCardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisionCardServiceImpl implements VisionCardService {

    private VisionCardRepository visionCardRepository;

    VisionCardServiceImpl(VisionCardRepository visionCardRepository){
        this.visionCardRepository = visionCardRepository;
    }

    @Override
    public VisionCardResponseDTO findById(Long id) {
        var visionCard = visionCardRepository.findById(id).orElseThrow(()->new ObjectNotFoundException("Visioncard não encontrada"));
        return VisionCardMapper.INSTANCE.convertToDTO(visionCard);
    }

    @Override
    public List<VisionCardResponseDTO> findAll() {
        return visionCardRepository.findAllVisionCards();
    }
}
