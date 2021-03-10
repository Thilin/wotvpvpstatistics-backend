package com.mxh.wotvpvpstatisticsbackend.models;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "tb_vision_card")
public class VisionCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VCD_ID")
    private Long id;

    @Column(name = "VCD_NAME")
    private String name;

    @Column(name = "VCD_IMAGE")
    private String image;
}
