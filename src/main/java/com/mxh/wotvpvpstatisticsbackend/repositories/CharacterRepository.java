package com.mxh.wotvpvpstatisticsbackend.repositories;

import com.mxh.wotvpvpstatisticsbackend.dtos.CharacterResponseDTO;
import com.mxh.wotvpvpstatisticsbackend.models.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CharacterRepository extends JpaRepository<Character, Long> {

    @Query("select new com.mxh.wotvpvpstatisticsbackend.dtos.CharacterResponseDTO(c.id, c.name, c.image, c.rarity, e.description) from Character c" +
            " inner join Element e on e.id = c.element.id order by c.id asc ")
    List<CharacterResponseDTO> findAllCharacters();

    Character findByName(String character1);
}
