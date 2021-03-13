package com.mxh.wotvpvpstatisticsbackend.repositories;

import com.mxh.wotvpvpstatisticsbackend.models.CharacterBuiltEquipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacterBuiltEquipmentRepository extends JpaRepository<CharacterBuiltEquipment, Long> {
    List<CharacterBuiltEquipment> findByCharacterBuiltId(Long id);
}
