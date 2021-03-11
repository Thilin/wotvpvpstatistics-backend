package com.mxh.wotvpvpstatisticsbackend.models;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_character_built_equipment")
@Builder
public class CharacterBuiltEquipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHE_ID")
    private Long id;

    @Column(name = "CHE_PLUS")
    private Long plus;

    @ManyToOne
    @JoinColumn(name = "CHE_EQP_ID")
    private Equipment equipment;

    @ManyToOne
    @JoinColumn(name = "CHE_CHB_ID")
    private CharacterBuilt characterBuilt;
}
