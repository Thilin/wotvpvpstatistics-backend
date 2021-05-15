package com.mxh.wotvpvpstatisticsbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfrontationTeamsDTO {

    private Long id;
    private String character1;
    private String character2;
    private String character3;
}
