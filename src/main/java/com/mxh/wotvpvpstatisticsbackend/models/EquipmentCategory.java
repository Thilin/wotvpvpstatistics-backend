package com.mxh.wotvpvpstatisticsbackend.models;

import lombok.*;

import javax.persistence.*;

@Table(name = "tb_equipment_category")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipmentCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ECA_ID")
    private Long id;

    @Column(name = "ECA_DESCRIPTION")
    private String description;
}
