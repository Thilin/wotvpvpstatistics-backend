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
@Builder
@Table(name = "tb_map")
public class Map {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAP_ID")
    private Long id;

    @Column(name = "MAP_NAME")
    private String name;
}
