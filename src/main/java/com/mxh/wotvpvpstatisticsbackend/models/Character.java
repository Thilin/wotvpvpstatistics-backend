package com.mxh.wotvpvpstatisticsbackend.models;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_character")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CTR_ID")
    private Long id;

    @Column(name = "CTR_NAME")
    private String name;

    @Column(name = "CTR_IMAGE")
    private String  image;

    @Column(name = "CTR_RARITY")
    private String rarity;

    @OneToOne
    @JoinColumn(name = "CTR_ELE_ID", referencedColumnName = "ELE_ID")
    private Element element;
}
