package com.mxh.wotvpvpstatisticsbackend.controllers;

import com.mxh.wotvpvpstatisticsbackend.dtos.CharacterBuiltCreateDTO;
import com.mxh.wotvpvpstatisticsbackend.dtos.ConfrontationDTO;
import com.mxh.wotvpvpstatisticsbackend.dtos.ConfrontationReportDTO;
import com.mxh.wotvpvpstatisticsbackend.dtos.ConfrontationTeamsDTO;
import com.mxh.wotvpvpstatisticsbackend.services.ConfrontationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/confrontations")
public class ConfrontationController {

    ConfrontationService confrontationService;

    ConfrontationController(ConfrontationService confrontationService){
        this.confrontationService = confrontationService;
    }

    @CrossOrigin
    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Register a confrontation result", description = "Register a new confrontation")
    public ResponseEntity<Void> create(@RequestBody @Valid ConfrontationDTO dto){
        Long id = confrontationService.register(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @CrossOrigin
    @GetMapping(value = "/all", produces = "application/json")
    @Operation(summary = "show all elements", description = "Show all elements informations")
    public ResponseEntity<List<ConfrontationTeamsDTO>> findAll(){
        return ResponseEntity.ok().body(confrontationService.findAll());
    }

    @GetMapping(value = "/{seasonId}")
    @Operation(summary = "List confrontations by SeasonId", description = "Show all confrontations by season id")
    public ResponseEntity<List<ConfrontationReportDTO>> confronationsBySeasonId(@PathVariable Long seasonId){
        return ResponseEntity.ok().body(confrontationService.findAllBySeasonId(seasonId));
    }
}
