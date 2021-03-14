package com.mxh.wotvpvpstatisticsbackend.repositories;

import com.mxh.wotvpvpstatisticsbackend.models.Formation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormationRepository extends JpaRepository<Formation, Long> {
    List<Formation> findByUserId(Long id);
}
