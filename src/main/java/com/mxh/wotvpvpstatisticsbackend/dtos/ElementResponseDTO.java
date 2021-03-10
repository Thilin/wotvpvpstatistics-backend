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
public class ElementResponseDTO {

    @Schema(description = "Element id", example = "1")
    private Long id;
    @Schema(description = "Element description", example = "Fire")
    private String description;
    @Schema(description = "Element image", example = "Fire.png")
    private String image;
}
