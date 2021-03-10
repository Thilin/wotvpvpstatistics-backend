package com.mxh.wotvpvpstatisticsbackend.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EsperResponseDTO {
    @Schema(description = "Esper id", example = "1")
    private Long id;
    @Schema(description = "Esper name", example = "Ifrit")
    private String name;
    @Schema(description = "Esper image", example = "Ifrit.png")
    private String image;
    @Schema(description = "Esper rarity", example = "UR")
    private String rarity;
    @Schema(description = "Esper element", example = "Fire")
    private String element;
}
