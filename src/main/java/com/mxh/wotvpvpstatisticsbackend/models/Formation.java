package com.mxh.wotvpvpstatisticsbackend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_formation")
@Builder
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FMT_ID")
    private Long id;

    @Column(name = "FMT_NAME")
    private String name;

    @Column(name = "FMT_ISATTACK")
    private boolean isAttack;

    @ManyToOne
    @JoinColumn(name = "FMT_USR_ID", referencedColumnName = "USR_ID")
    private User user;
}
