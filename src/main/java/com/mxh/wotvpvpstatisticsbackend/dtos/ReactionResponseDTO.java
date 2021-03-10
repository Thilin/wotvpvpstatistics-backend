package com.mxh.wotvpvpstatisticsbackend.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReactionResponseDTO {

    @Schema(description = "Reaction id", example = "1")
    private Long id;
    @Schema(description = "Reaction description", example = "Counter Attack")
    private String description;
    @Schema(description = "Job id", example = "1")
    private Long jobId;
    @Schema(description = "Job description", example = "Lord")
    private String jobDescription;
    @Schema(description = "Job image", example = "Lord.png")
    private String image;

}
