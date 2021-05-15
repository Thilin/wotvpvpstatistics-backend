package com.mxh.wotvpvpstatisticsbackend.controllers;

import com.mxh.wotvpvpstatisticsbackend.dtos.CharacterResponseDTO;
import com.mxh.wotvpvpstatisticsbackend.services.CharacterService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/characters")
public class CharacterController {

    private CharacterService characterService;

    CharacterController(CharacterService characterService){
        this.characterService = characterService;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "Find a character by id", description = "Find a character using its id")
    public ResponseEntity<CharacterResponseDTO> findById(@PathVariable Long id){
        var dto = characterService.findById(id);
        if(dto == null){
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok().body(dto);
        }
    }

    @CrossOrigin
    @GetMapping(value = "/all", produces = "application/json")
    @Operation(summary = "show all characters", description = "Show all characters informations")
    public ResponseEntity<List<CharacterResponseDTO>> findAll(){
        return ResponseEntity.ok().body(characterService.findAll());
    }

}
