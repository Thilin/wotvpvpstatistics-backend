package com.mxh.wotvpvpstatisticsbackend.controllers;

import com.mxh.wotvpvpstatisticsbackend.dtos.CharacterBuiltCreateDTO;
import com.mxh.wotvpvpstatisticsbackend.dtos.CharacterBuiltResponseDTO;
import com.mxh.wotvpvpstatisticsbackend.services.CharacterBuiltService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/builder")
public class CharacterBuiltController {

    CharacterBuiltService characterBuiltService;

    CharacterBuiltController(CharacterBuiltService characterBuiltService){
        this.characterBuiltService = characterBuiltService;
    }

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Create a characterBuild", description = "Create a new CharacterBuild")
    public ResponseEntity<Void> create(@RequestBody @Valid CharacterBuiltCreateDTO dto){
        Long id = characterBuiltService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "Find a characterbuilt by id", description = "Find a characterbuilt using its id")
    public ResponseEntity<CharacterBuiltResponseDTO> findById(@PathVariable Long id){
        var dto = characterBuiltService.findById(id);
        if(dto == null){
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok().body(dto);
        }
    }

    @GetMapping(value = "/all/user/{id}", produces = "application/json")
    @Operation(summary = "show all characterBuilt by user Id", description = "Show all character builds informations by user Id")
    public ResponseEntity<List<CharacterBuiltResponseDTO>> findAll(@PathVariable Long id){
        return ResponseEntity.ok().body(characterBuiltService.findAllByUserId(id));
    }

    @PutMapping(value = "/{buildId}")
    @Operation(summary = "Update a characterBuild by Id", description = "Update a character build by Id")
    public ResponseEntity<Void> update(@PathVariable Long buildId, @RequestBody CharacterBuiltCreateDTO dto){
        characterBuiltService.update(buildId, dto);
        return ResponseEntity.ok().build();
    }
}
