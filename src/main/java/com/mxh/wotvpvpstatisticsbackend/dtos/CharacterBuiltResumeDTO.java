package com.mxh.wotvpvpstatisticsbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharacterBuiltResumeDTO {

    private Long id;
    private String name;
    private String esper;
    private String visionCard;
    private List<EquipmentResumeDTO> equipments;
}
