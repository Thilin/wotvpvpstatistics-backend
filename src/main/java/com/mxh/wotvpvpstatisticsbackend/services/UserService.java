package com.mxh.wotvpvpstatisticsbackend.services;

import com.mxh.wotvpvpstatisticsbackend.dtos.UserCreateDTO;
import com.mxh.wotvpvpstatisticsbackend.dtos.UserResponseDTO;

public interface UserService {

    UserResponseDTO create(UserCreateDTO dto);

    UserResponseDTO findByEmail(String email);
}
