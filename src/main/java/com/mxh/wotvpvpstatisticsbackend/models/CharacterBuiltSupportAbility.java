package com.mxh.wotvpvpstatisticsbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_character_built_support_ability")
public class CharacterBuiltSupportAbility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CSH_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CSH_CHB_ID", referencedColumnName = "CHB_ID")
    private CharacterBuilt characterBuilt;

    @ManyToOne
    @JoinColumn(name = "CSH_SAB_ID")
    private SupportAbility supportAbility;
}
