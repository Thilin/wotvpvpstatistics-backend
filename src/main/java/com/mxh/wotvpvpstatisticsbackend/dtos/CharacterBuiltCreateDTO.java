package com.mxh.wotvpvpstatisticsbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CharacterBuiltCreateDTO {

    private String name;
    private Long visionCardId;
    private Long characterId;
    private Long userId;
    private Long esperId;
    private Long reactionId;
    private List<CharacterBuiltEquipmentDTO> equipmentsDTO;
    private List<Long> supportAbilitiesId;
    private List<Long> jobsId;
}
