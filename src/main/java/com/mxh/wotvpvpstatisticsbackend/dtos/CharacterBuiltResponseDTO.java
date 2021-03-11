package com.mxh.wotvpvpstatisticsbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CharacterBuiltResponseDTO {

    private Long id;
    private String name;
    private String character;
    private String esper;
    private String visionCard;
    private String reaction;
    private List<JobResponseDTO> jobs;
}
