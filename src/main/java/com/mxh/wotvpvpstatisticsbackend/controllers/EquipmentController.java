package com.mxh.wotvpvpstatisticsbackend.controllers;

import com.mxh.wotvpvpstatisticsbackend.dtos.EquipmentResponseDTO;
import com.mxh.wotvpvpstatisticsbackend.services.EquipmentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/equipments")
public class EquipmentController {

    EquipmentService equipmentService;

    EquipmentController(EquipmentService equipmentService){
        this.equipmentService = equipmentService;
    }


    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "Find an equipment by id", description = "Find an equipment using its id")
    public ResponseEntity<EquipmentResponseDTO> findById(@PathVariable Long id){
        var dto = equipmentService.findById(id);
        if(dto == null){
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok().body(equipmentService.findById(id));
        }
    }

    @GetMapping(value = "/all", produces = "application/json")
    @Operation(summary = "show all equipments", description = "Show all equipments informations")
    public ResponseEntity<List<EquipmentResponseDTO>> findAll(){
        return ResponseEntity.ok().body(equipmentService.findAll());
    }
}
