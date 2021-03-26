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
@Table(name = "tb_character_job_confrontation_detail")
public class CharacterJobConfrontationDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CJC_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CJC_CJO_ID")
    private CharacterJob characterJob;

    @ManyToOne
    @JoinColumn(name = "CJC_COD_ID")
    private ConfrontationDetail confrontationDetail;
}
