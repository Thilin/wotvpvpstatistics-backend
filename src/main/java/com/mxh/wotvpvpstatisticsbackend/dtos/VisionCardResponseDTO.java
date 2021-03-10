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

    @Schema(description = "Job id", example = "1")
    private Long id;
    @Schema(description = "Job description", example = "Lord")
    private String name;
    @Schema(description = "Job image", example = "Lord.png")
    private String image;
}
