package com.mxh.wotvpvpstatisticsbackend.controllers;

import com.mxh.wotvpvpstatisticsbackend.dtos.EsperResponseDTO;
import com.mxh.wotvpvpstatisticsbackend.dtos.JobResponseDTO;
import com.mxh.wotvpvpstatisticsbackend.dtos.ReactionResponseDTO;
import com.mxh.wotvpvpstatisticsbackend.services.ReactionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/reactions")
public class ReactionController {

    private ReactionService reactionService;

    ReactionController(ReactionService reactionService){
        this.reactionService = reactionService;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "Find a reaction by id", description = "Find a reaction using its id")
    public ResponseEntity<ReactionResponseDTO> findById(@PathVariable Long id){
        var dto = reactionService.findById(id);
        if(dto == null){
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok().body(dto);
        }
    }

    @GetMapping(value = "/all", produces = "application/json")
    @Operation(summary = "show all reactions", description = "Show all reactions informations")
    public ResponseEntity<List<ReactionResponseDTO>> findAll(){
        return ResponseEntity.ok().body(reactionService.findAll());
    }
}
