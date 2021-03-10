package com.mxh.wotvpvpstatisticsbackend.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VisionCardResponseDTO {

    @Schema(description = "VisionCard id", example = "1")
    private Long id;
    @Schema(description = "VisionCard description", example = "Lord")
    private String name;
    @Schema(description = "VisionCard image", example = "Lord.png")
    private String image;
}
