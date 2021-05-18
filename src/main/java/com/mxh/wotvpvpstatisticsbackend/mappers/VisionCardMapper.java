package com.mxh.wotvpvpstatisticsbackend.mappers;

import com.mxh.wotvpvpstatisticsbackend.dtos.VisionCardResponseDTO;
import com.mxh.wotvpvpstatisticsbackend.models.VisionCard;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VisionCardMapper {

    VisionCardMapper  INSTANCE = Mappers.getMapper(VisionCardMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "image", target = "image")
    VisionCardResponseDTO convertToDTO(VisionCard entity);
}
