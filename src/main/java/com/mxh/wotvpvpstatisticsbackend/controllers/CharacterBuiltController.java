package com.mxh.wotvpvpstatisticsbackend.controllers;

import com.mxh.wotvpvpstatisticsbackend.dtos.CharacterBuiltCreateDTO;
import com.mxh.wotvpvpstatisticsbackend.services.CharacterBuiltService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

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
}
