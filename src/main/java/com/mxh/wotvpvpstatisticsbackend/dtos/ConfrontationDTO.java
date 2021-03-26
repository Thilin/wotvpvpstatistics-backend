package com.mxh.wotvpvpstatisticsbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfrontationDTO {

    private Long userId;
    private Long formationId;
    private Long seasonId;
    private LocalDate date;
    private Long losses;
    private Long defeats;
    private boolean isWin;

}
