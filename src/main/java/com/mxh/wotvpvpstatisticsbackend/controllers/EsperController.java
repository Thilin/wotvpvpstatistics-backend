package com.mxh.wotvpvpstatisticsbackend.controllers;

import com.mxh.wotvpvpstatisticsbackend.dtos.EsperResponseDTO;
import com.mxh.wotvpvpstatisticsbackend.services.EsperService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/espers")
public class EsperController {

    private EsperService esperService;

    EsperController(EsperService esperService){
        this.esperService = esperService;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "Find an esper by id", description = "Find an esper using its id")
    public ResponseEntity<EsperResponseDTO> findById(@PathVariable Long id){
        var dto = esperService.findById(id);
        if(dto == null){
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok().body(dto);
        }
    }

    @GetMapping(value = "/all", produces = "application/json")
    @Operation(summary = "show all espers", description = "Show all espers informations")
    public ResponseEntity<List<EsperResponseDTO>> findAll(){
        return ResponseEntity.ok().body(esperService.findAll());
    }
}
