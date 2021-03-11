package com.mxh.wotvpvpstatisticsbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_support_ability")
public class SupportAbility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SAB_ID")
    private Long id;

    @Column(name = "SAB_DESCRIPTION")
    private String description;

    @OneToOne
    @JoinColumn(name = "SAB_JOB_ID", referencedColumnName = "JOB_ID")
    private Job job;
}
