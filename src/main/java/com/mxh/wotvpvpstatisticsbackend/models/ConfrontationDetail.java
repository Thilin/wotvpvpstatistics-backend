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
@Table(name = "tb_confrontation_detail")
public class ConfrontationDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_ID")
    private Long id;

    @Column(name = "COD_POSITION")
    private Long position;

    @OneToOne
    @JoinColumn(name = "COD_CTR_ID", referencedColumnName = "CTR_ID")
    private Character character;

    @ManyToOne
    @JoinColumn(name = "COD_CON_ID")
    private Confrontation confrontation;

    @OneToOne
    @JoinColumn(name = "COD_VCD_ID", referencedColumnName = "VCD_ID")
    private VisionCard visionCard;

    @OneToOne
    @JoinColumn(name = "COD_ESP_ID", referencedColumnName = "ESP_ID")
    private Esper esper;

    @OneToOne
    @JoinColumn(name = "COD_REA_ID", referencedColumnName = "REA_ID")
    private Reaction reaction;

}
