package com.mxh.wotvpvpstatisticsbackend.repositories;

import com.mxh.wotvpvpstatisticsbackend.models.Opponent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpponentRepository extends JpaRepository<Opponent, Long> {
}
