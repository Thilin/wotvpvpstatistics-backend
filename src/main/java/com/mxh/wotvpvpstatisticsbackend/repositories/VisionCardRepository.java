package com.mxh.wotvpvpstatisticsbackend.repositories;

import com.mxh.wotvpvpstatisticsbackend.dtos.VisionCardResponseDTO;
import com.mxh.wotvpvpstatisticsbackend.models.VisionCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VisionCardRepository extends JpaRepository<VisionCard, Long> {
    @Query("select new com.mxh.wotvpvpstatisticsbackend.dtos.VisionCardResponseDTO(v.id, v.name, v.image) from VisionCard v")
    List<VisionCardResponseDTO> findAllVisionCards();
}
