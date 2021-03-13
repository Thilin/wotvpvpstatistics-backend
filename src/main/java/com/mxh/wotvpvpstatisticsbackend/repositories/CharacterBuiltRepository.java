package com.mxh.wotvpvpstatisticsbackend.repositories;

import com.mxh.wotvpvpstatisticsbackend.models.CharacterBuilt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacterBuiltRepository extends JpaRepository<CharacterBuilt, Long> {
    List<CharacterBuilt> findByUserId(Long id);
}
