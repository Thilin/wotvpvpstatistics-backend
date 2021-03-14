package com.mxh.wotvpvpstatisticsbackend.repositories;

import com.mxh.wotvpvpstatisticsbackend.models.FormationCharacterBuilt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormationCharacterBuiltRepository extends JpaRepository<FormationCharacterBuilt, Long> {
    List<FormationCharacterBuilt> findByFormationId(Long id);
}
