package com.mxh.wotvpvpstatisticsbackend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "tb_confrontation_character_formation")
public class ConfrontationCharacterFormation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CCF_ID")
    private Long id;

    @Column(name = "CCF_CTR_NAME_1")
    private String character1;

    @Column(name = "CCF_CTR_NAME_2")
    private String character2;

    @Column(name = "CCF_CTR_NAME_3")
    private String character3;
}
