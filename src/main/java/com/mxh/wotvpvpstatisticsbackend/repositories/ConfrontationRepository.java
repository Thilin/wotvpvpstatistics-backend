package com.mxh.wotvpvpstatisticsbackend.repositories;

import com.mxh.wotvpvpstatisticsbackend.models.Confrontation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConfrontationRepository extends JpaRepository<Confrontation, Long> {
    List<Confrontation> findBySeasonId(Long seasonId);
}
