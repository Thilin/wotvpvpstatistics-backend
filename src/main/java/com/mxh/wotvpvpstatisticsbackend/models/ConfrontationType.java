package com.mxh.wotvpvpstatisticsbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_confrontation_type")
public class ConfrontationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CTY_ID")
    private Long id;

    @Column(name = "CTY_DESCRIPTION")
    private String description;
}
