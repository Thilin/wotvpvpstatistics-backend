package com.mxh.wotvpvpstatisticsbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormationResponseDTO {

    private Long id;
    private String name;
    private List<CharacterBuiltResumeDTO> characters;
}
