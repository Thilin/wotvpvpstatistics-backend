package com.mxh.wotvpvpstatisticsbackend.dtos;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CharacterResponseDTO {
    @Schema(description = "Character id", example = "1")
    private Long id;
    @Schema(description = "Character name", example = "Ayaka")
    private String name;
    @Schema(description = "Character image", example = "Ayaka.png")
    private String image;
    @Schema(description = "Character rarity", example = "UR")
    private String rarity;
    @Schema(description = "Character element", example = "Fire")
    private String element;
}
