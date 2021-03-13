package com.mxh.wotvpvpstatisticsbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentResponseDTO {

    private Long id;
    private Long plus;
    private String name;
    private String rarity;
    private boolean isTMR;
    private String type;
    private String category;
}
