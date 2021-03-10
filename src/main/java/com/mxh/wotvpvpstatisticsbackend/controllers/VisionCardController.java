package com.mxh.wotvpvpstatisticsbackend.controllers;

import com.mxh.wotvpvpstatisticsbackend.dtos.JobResponseDTO;
import com.mxh.wotvpvpstatisticsbackend.dtos.VisionCardResponseDTO;
import com.mxh.wotvpvpstatisticsbackend.services.VisionCardService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/visions")
public class VisionCardController {

    private VisionCardService visionCardService;

    VisionCardController(VisionCardService visionCardService){
        this.visionCardService = visionCardService;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "Find a visionCard by id", description = "Find a visionCard using its id")
    public ResponseEntity<VisionCardResponseDTO> findById(@PathVariable Long id){
        var dto = visionCardService.findById(id);
        if(dto == null){
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok().body(dto);
        }
    }

    @GetMapping(value = "/all", produces = "application/json")
    @Operation(summary = "show all visionCards", description = "Show all visionCards informations")
    public ResponseEntity<List<VisionCardResponseDTO>> findAll(){
        return ResponseEntity.ok().body(visionCardService.findAll());
    }
}
