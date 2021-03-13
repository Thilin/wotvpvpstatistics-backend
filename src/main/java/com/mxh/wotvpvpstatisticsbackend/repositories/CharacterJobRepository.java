package com.mxh.wotvpvpstatisticsbackend.repositories;

import com.mxh.wotvpvpstatisticsbackend.models.CharacterJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CharacterJobRepository extends JpaRepository<CharacterJob, Long> {
    Optional<CharacterJob> findByCharacterIdAndJobId(Long id, Long jobId);

    List<CharacterJob> findByCharacterId(Long id);
}
