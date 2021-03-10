package com.mxh.wotvpvpstatisticsbackend.models;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USR_ID")
    private Long id;

    @Column(name = "USR_NAME")
    private String name;

    @Column(name = "USR_NICKNAME")
    private String nickName;

    @Column(name = "USR_EMAIL")
    private String email;

    @Column(name = "USR_PASSWORD")
    private String password;
}
