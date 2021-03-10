package com.mxh.wotvpvpstatisticsbackend.repositories;

import com.mxh.wotvpvpstatisticsbackend.dtos.ReactionResponseDTO;
import com.mxh.wotvpvpstatisticsbackend.models.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {
    @Query("select new com.mxh.wotvpvpstatisticsbackend.dtos.ReactionResponseDTO(r.id, r.description, j.id, j.description, j.image) from Reaction r" +
            " inner join Job j on j.id = r.job.id")
    List<ReactionResponseDTO> findALlReactions();
}
