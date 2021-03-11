package com.mxh.wotvpvpstatisticsbackend.services;

import com.mxh.wotvpvpstatisticsbackend.dtos.CharacterBuiltCreateDTO;

public interface CharacterBuiltService {

    Long create(CharacterBuiltCreateDTO dto);
}
