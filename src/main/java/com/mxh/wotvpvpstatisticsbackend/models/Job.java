package com.mxh.wotvpvpstatisticsbackend.models;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_job")
@Builder
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JOB_ID")
    private Long id;

    @Column(name = "JOB_DESCRIPTION")
    private String description;

    @Column(name = "JOB_IMAGE")
    private String image;
}
