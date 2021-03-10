package com.mxh.wotvpvpstatisticsbackend.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobResponseDTO {

    @Schema(description = "Job id", example = "1")
    private Long id;
    @Schema(description = "Job description", example = "Lord")
    private String description;
    @Schema(description = "Job image", example = "Lord.png")
    private String image;
}
