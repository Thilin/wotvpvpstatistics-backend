package com.mxh.wotvpvpstatisticsbackend.controllers;

import com.mxh.wotvpvpstatisticsbackend.dtos.ElementResponseDTO;
import com.mxh.wotvpvpstatisticsbackend.services.ElementService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/elements")
public class ElementController {

    private ElementService elementService;

    ElementController(ElementService elementService){
        this.elementService = elementService;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "Find an element by id", description = "Find an element using its id")
    public ResponseEntity<ElementResponseDTO> findById(@PathVariable Long id){
        var dto = elementService.findById(id);
        if(dto == null){
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok().body(elementService.findById(id));
        }
    }

    @GetMapping(value = "/all", produces = "application/json")
    @Operation(summary = "show all elements", description = "Show all elements informations")
    public ResponseEntity<List<ElementResponseDTO>> findAll(){
        return ResponseEntity.ok().body(elementService.findAll());
    }
}
