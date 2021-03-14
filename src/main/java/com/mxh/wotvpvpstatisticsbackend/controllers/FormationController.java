package com.mxh.wotvpvpstatisticsbackend.controllers;

import com.mxh.wotvpvpstatisticsbackend.dtos.CharacterBuiltCreateDTO;
import com.mxh.wotvpvpstatisticsbackend.dtos.CharacterBuiltResponseDTO;
import com.mxh.wotvpvpstatisticsbackend.dtos.FormationCreateDTO;
import com.mxh.wotvpvpstatisticsbackend.dtos.FormationResponseDTO;
import com.mxh.wotvpvpstatisticsbackend.services.FormationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/formations")
public class FormationController {

    FormationService formationService;

    FormationController(FormationService formationService){
        this.formationService = formationService;
    }

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Create a formation", description = "Create a new formation")
    public ResponseEntity<Void> create(@RequestBody @Valid FormationCreateDTO dto){
        Long id = formationService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "Find a formation by id", description = "Find a formation using its id")
    public ResponseEntity<FormationResponseDTO> findById(@PathVariable Long id){
        var dto = formationService.findById(id);
        if(dto == null){
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok().body(dto);
        }
    }

    @GetMapping(value = "/all/user/{id}", produces = "application/json")
    @Operation(summary = "show all characterBuilt by user Id", description = "Show all character builds informations by user Id")
    public ResponseEntity<List<FormationResponseDTO>> findAll(@PathVariable Long id){
        return ResponseEntity.ok().body(formationService.findByUserId(id));
    }
}
