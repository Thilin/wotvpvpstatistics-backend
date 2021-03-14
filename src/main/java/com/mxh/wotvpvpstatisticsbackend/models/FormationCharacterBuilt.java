package com.mxh.wotvpvpstatisticsbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "tb_formation_character_built")
public class FormationCharacterBuilt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FCB_ID")
    private Long id;

    @Column(name = "FCB_ORDER")
    private Long order;

    @ManyToOne
    @JoinColumn(name = "FCB_FMT_ID")
    private Formation formation;

    @ManyToOne
    @JoinColumn(name = "FCB_CHB_ID")
    private CharacterBuilt characterBuilt;
}
