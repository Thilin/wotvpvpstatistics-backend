package com.mxh.wotvpvpstatisticsbackend.services.impl;

import com.mxh.wotvpvpstatisticsbackend.dtos.CharacterResponseDTO;
import com.mxh.wotvpvpstatisticsbackend.exceptions.ObjectNotFoundException;
import com.mxh.wotvpvpstatisticsbackend.repositories.CharacterRepository;
import com.mxh.wotvpvpstatisticsbackend.services.CharacterService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {

    private CharacterRepository characterRepository;
    CharacterServiceImpl(CharacterRepository characterRepository){
        this.characterRepository = characterRepository;
    }
    @Override
    public CharacterResponseDTO findById(Long id) {
        var character = characterRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Character não encontrado"));
        var dto = new CharacterResponseDTO();
        dto.setId(character.getId());
        dto.setName(character.getName());
        dto.setRarity(character.getRarity());
        dto.setImage(character.getImage());
        dto.setElement(character.getElement().getDescription());
        return dto;
    }

    @Override
    public List<CharacterResponseDTO> findAll() {
        return characterRepository.findAllCharacters();
    }
}
