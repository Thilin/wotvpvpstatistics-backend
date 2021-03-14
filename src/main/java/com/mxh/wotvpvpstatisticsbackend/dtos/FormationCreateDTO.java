package com.mxh.wotvpvpstatisticsbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormationCreateDTO {

    private Long userId;
    private String name;
    List<FormationCharacterBuiltCreateDTO> characters;
}
