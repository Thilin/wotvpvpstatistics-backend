package com.mxh.wotvpvpstatisticsbackend.repositories;

import com.mxh.wotvpvpstatisticsbackend.models.CharacterBuiltJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CharacterBuiltJobRepository  extends JpaRepository<CharacterBuiltJob, Long> {
    List<CharacterBuiltJob> findByCharacterBuiltId(Long id);
}
