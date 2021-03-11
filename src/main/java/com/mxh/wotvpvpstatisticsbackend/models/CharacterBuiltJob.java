package com.mxh.wotvpvpstatisticsbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_character_built_job")
public class CharacterBuiltJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CBJ_ID")
    private Long id;

    @Column(name = "CBJ_ISMAIN")
    private boolean isMain;

    @ManyToOne
    @JoinColumn(name = "CBJ_CHB_ID")
    private CharacterBuilt characterBuilt;

    @ManyToOne
    @JoinColumn(name = "CBJ_CJO_ID")
    private CharacterJob characterJob;
}
