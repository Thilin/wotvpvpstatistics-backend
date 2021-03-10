package com.mxh.wotvpvpstatisticsbackend.controllers;

import com.mxh.wotvpvpstatisticsbackend.dtos.CharacterResponseDTO;
import com.mxh.wotvpvpstatisticsbackend.dtos.UserCreateDTO;
import com.mxh.wotvpvpstatisticsbackend.dtos.UserResponseDTO;
import com.mxh.wotvpvpstatisticsbackend.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private UserService userService;

    UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Create an user", description = "Create an user to use the application")
    public ResponseEntity<Void> create(@RequestBody @Valid UserCreateDTO dto){
        var responseDTO = userService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(responseDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/{email}", produces = "application/json")
    @Operation(summary = "Find an user by email", description = "Find an user using its email")
    public ResponseEntity<UserResponseDTO> findByEmail(@PathVariable String email){
        var dto = userService.findByEmail(email);
        if(dto == null){
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok().body(dto);
        }
    }
}
