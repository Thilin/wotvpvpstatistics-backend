package com.mxh.wotvpvpstatisticsbackend.repositories;

import com.mxh.wotvpvpstatisticsbackend.dtos.SeasonDTO;
import com.mxh.wotvpvpstatisticsbackend.models.Season;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeasonRepository extends JpaRepository<Season, Long> {
    List<Season> findByConfrontationTypeId(Long confrontationTypeId);
}
