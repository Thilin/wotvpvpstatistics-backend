package com.mxh.wotvpvpstatisticsbackend.services.impl;

import com.mxh.wotvpvpstatisticsbackend.dtos.UserCreateDTO;
import com.mxh.wotvpvpstatisticsbackend.dtos.UserResponseDTO;
import com.mxh.wotvpvpstatisticsbackend.models.User;
import com.mxh.wotvpvpstatisticsbackend.repositories.UserRepository;
import com.mxh.wotvpvpstatisticsbackend.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDTO create(UserCreateDTO dto) {
        var user = new User();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setNickName(dto.getNickName());
        user.setPassword(dto.getPassword());

        userRepository.save(user);
        var responseDTO = new UserResponseDTO();
        responseDTO.setId(user.getId());
        responseDTO.setName(user.getName());
        responseDTO.setNickName(user.getNickName());
        responseDTO.setEmail(user.getEmail());
        return responseDTO;
    }
}