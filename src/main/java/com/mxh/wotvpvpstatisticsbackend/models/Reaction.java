package com.mxh.wotvpvpstatisticsbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_reaction")
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REA_ID")
    private Long id;

    @Column(name = "REA_DESCRIPTION")
    private String description;

    @OneToOne
    @JoinColumn(name = "REA_JOB_ID", referencedColumnName = "JOB_ID")
    private Job job;
}
