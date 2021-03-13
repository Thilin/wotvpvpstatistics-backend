package com.mxh.wotvpvpstatisticsbackend.repositories;

import com.mxh.wotvpvpstatisticsbackend.models.CharacterBuiltSupportAbility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CharacterBuiltSupportAbilityRepository extends JpaRepository<CharacterBuiltSupportAbility, Long> {
    List<CharacterBuiltSupportAbility> findByCharacterBuiltId(Long id);
}
