package com.mxh.wotvpvpstatisticsbackend.repositories;

import com.mxh.wotvpvpstatisticsbackend.models.Season;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeasonRepository extends JpaRepository<Season, Long> {
}
