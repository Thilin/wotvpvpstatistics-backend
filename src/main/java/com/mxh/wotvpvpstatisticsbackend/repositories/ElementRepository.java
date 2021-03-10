package com.mxh.wotvpvpstatisticsbackend.repositories;

import com.mxh.wotvpvpstatisticsbackend.dtos.ElementResponseDTO;
import com.mxh.wotvpvpstatisticsbackend.models.Element;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ElementRepository extends JpaRepository<Element, Long> {

    @Query("select new com.mxh.wotvpvpstatisticsbackend.dtos.ElementResponseDTO(ele.id, ele.description, ele.description) from Element ele")
    List<ElementResponseDTO> findAllElements();
}
