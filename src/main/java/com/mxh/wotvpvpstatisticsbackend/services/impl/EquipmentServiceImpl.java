package com.mxh.wotvpvpstatisticsbackend.services.impl;

import com.mxh.wotvpvpstatisticsbackend.dtos.EquipmentResponseDTO;
import com.mxh.wotvpvpstatisticsbackend.exceptions.ObjectNotFoundException;
import com.mxh.wotvpvpstatisticsbackend.models.Equipment;
import com.mxh.wotvpvpstatisticsbackend.repositories.EquipmentRepository;
import com.mxh.wotvpvpstatisticsbackend.services.EquipmentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    EquipmentRepository equipmentRepository;

    EquipmentServiceImpl(EquipmentRepository equipmentRepository){
        this.equipmentRepository = equipmentRepository;
    }
    @Override
    public EquipmentResponseDTO findById(Long id) {
        var equipment = equipmentRepository.findById(id).orElseThrow(()->new ObjectNotFoundException("Equipmento não encontrado"));
        var dto = new EquipmentResponseDTO();
        dto.setName(equipment.getName());
        dto.setId(equipment.getId());
        dto.setTMR(equipment.isTMR());
        dto.setRarity(equipment.getRarity());
        dto.setType(equipment.getEquipmentType().getDescription());
        dto.setCategory(equipment.getEquipmentCategory().getDescription());
        return dto;
    }

    @Override
    public List<EquipmentResponseDTO> findAll() {
        List<EquipmentResponseDTO> dtos = new ArrayList<>();
        List<Equipment> equipments = equipmentRepository.findAll();
        equipments.forEach(equipment -> {
            var dto = new EquipmentResponseDTO();
            dto.setName(equipment.getName());
            dto.setId(equipment.getId());
            dto.setTMR(equipment.isTMR());
            dto.setRarity(equipment.getRarity());
            dto.setType(equipment.getEquipmentType().getDescription());
            dto.setCategory(equipment.getEquipmentCategory().getDescription());
            dtos.add(dto);
        });
        return dtos;
    }
}
