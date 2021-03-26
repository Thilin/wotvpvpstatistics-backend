package com.mxh.wotvpvpstatisticsbackend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_equipment_confrontation_detail")
public class EquipmentConfrontationDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ECD_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ECD_COD_ID")
    private ConfrontationDetail confrontationDetail;

    @ManyToOne
    @JoinColumn(name = "ECD_EQP_ID")
    private Equipment equipment;
}
