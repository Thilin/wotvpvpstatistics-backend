package com.mxh.wotvpvpstatisticsbackend.repositories;

import com.mxh.wotvpvpstatisticsbackend.dtos.EsperResponseDTO;
import com.mxh.wotvpvpstatisticsbackend.models.Esper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EsperRepository extends JpaRepository<Esper, Long> {

    @Query("select new com.mxh.wotvpvpstatisticsbackend.dtos.EsperResponseDTO(e.id, e.name, e.image, e.rarity, ele.description) from Esper e" +
            " inner join Element  ele on ele.id=e.element.id order by e.id asc ")
    List<EsperResponseDTO> findAllEspers();
}
