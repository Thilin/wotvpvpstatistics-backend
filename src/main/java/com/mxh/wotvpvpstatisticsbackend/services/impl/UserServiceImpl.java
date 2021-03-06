package com.mxh.wotvpvpstatisticsbackend.services.impl;

import com.mxh.wotvpvpstatisticsbackend.dtos.UserCreateDTO;
import com.mxh.wotvpvpstatisticsbackend.dtos.UserResponseDTO;
import com.mxh.wotvpvpstatisticsbackend.exceptions.EmailDuplicatedException;
import com.mxh.wotvpvpstatisticsbackend.exceptions.ObjectNotFoundException;
import com.mxh.wotvpvpstatisticsbackend.models.User;
import com.mxh.wotvpvpstatisticsbackend.repositories.UserRepository;
import com.mxh.wotvpvpstatisticsbackend.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDTO create(UserCreateDTO dto) {
        if(userRepository.existsByEmail(dto.getEmail()))
            throw new EmailDuplicatedException("Já tem um cadastro com este email");

        var user = new User();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setNickName(dto.getNickName());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        userRepository.save(user);
        var responseDTO = new UserResponseDTO();
        responseDTO.setId(user.getId());
        responseDTO.setName(user.getName());
        responseDTO.setNickName(user.getNickName());
        responseDTO.setEmail(user.getEmail());
        return responseDTO;
    }

    @Override
    public UserResponseDTO findByEmail(String email) {
        var user = userRepository.findByEmail(email).orElseThrow(()-> new ObjectNotFoundException("Usuário não encontrado"));
        var dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setNickName(user.getNickName());
        dto.setName(user.getName());
        return dto;
    }
}
