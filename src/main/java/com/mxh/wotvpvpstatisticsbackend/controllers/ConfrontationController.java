package com.mxh.wotvpvpstatisticsbackend.controllers;

import com.mxh.wotvpvpstatisticsbackend.dtos.CharacterBuiltCreateDTO;
import com.mxh.wotvpvpstatisticsbackend.dtos.ConfrontationDTO;
import com.mxh.wotvpvpstatisticsbackend.services.ConfrontationService;
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
@RequestMapping(value = "/confrontations")
public class ConfrontationController {

    ConfrontationService confrontationService;

    ConfrontationController(ConfrontationService confrontationService){
        this.confrontationService = confrontationService;
    }

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Register a confrontation result", description = "Register a new confrontation")
    public ResponseEntity<Void> create(@RequestBody @Valid ConfrontationDTO dto){
        Long id = confrontationService.register(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }
}
