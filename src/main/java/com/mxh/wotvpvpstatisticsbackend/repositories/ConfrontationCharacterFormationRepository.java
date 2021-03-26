package com.mxh.wotvpvpstatisticsbackend.repositories;

import com.mxh.wotvpvpstatisticsbackend.models.ConfrontationCharacterFormation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfrontationCharacterFormationRepository extends JpaRepository<ConfrontationCharacterFormation, Long> {

    ConfrontationCharacterFormation findByCharacter1AndCharacter2AndCharacter3(String char1, String char2, String char3);
}
