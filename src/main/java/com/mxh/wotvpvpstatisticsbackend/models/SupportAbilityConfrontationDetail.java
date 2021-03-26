package com.mxh.wotvpvpstatisticsbackend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_support_ability_confrontation_detail")
public class SupportAbilityConfrontationDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SAD_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "SAD_COD_ID")
    private ConfrontationDetail confrontationDetail;

    @ManyToOne
    @JoinColumn(name = "SAD_SAP_ID")
    private SupportAbility supportAbility;
}
