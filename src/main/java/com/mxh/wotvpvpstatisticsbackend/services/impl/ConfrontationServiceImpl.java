package com.mxh.wotvpvpstatisticsbackend.services.impl;

import com.mxh.wotvpvpstatisticsbackend.dtos.ConfrontationDTO;
import com.mxh.wotvpvpstatisticsbackend.exceptions.ObjectNotFoundException;
import com.mxh.wotvpvpstatisticsbackend.models.Confrontation;
import com.mxh.wotvpvpstatisticsbackend.models.ConfrontationCharacterFormation;
import com.mxh.wotvpvpstatisticsbackend.models.ConfrontationDetail;
import com.mxh.wotvpvpstatisticsbackend.models.FormationCharacterBuilt;
import com.mxh.wotvpvpstatisticsbackend.repositories.*;
import com.mxh.wotvpvpstatisticsbackend.services.ConfrontationDetailService;
import com.mxh.wotvpvpstatisticsbackend.services.ConfrontationService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ConfrontationServiceImpl implements ConfrontationService {

    ConfrontationRepository confrontationRepository;
    ConfrontationCharacterFormationRepository confrontationCharacterFormationRepository;
    FormationRepository formationRepository;
    FormationCharacterBuiltRepository formationCharacterBuiltRepository;
    UserRepository userRepository;
    SeasonRepository seasonRepository;
    ConfrontationDetailService confrontationDetailService;

    ConfrontationServiceImpl(ConfrontationRepository confrontationRepository, ConfrontationCharacterFormationRepository confrontationCharacterFormationRepository,
                             FormationRepository formationRepository, FormationCharacterBuiltRepository formationCharacterBuiltRepository,
                             UserRepository userRepository, SeasonRepository seasonRepository, ConfrontationDetailService confrontationDetailService){
        this.confrontationRepository = confrontationRepository;
        this.confrontationCharacterFormationRepository = confrontationCharacterFormationRepository;
        this.formationRepository = formationRepository;
        this.formationCharacterBuiltRepository = formationCharacterBuiltRepository;
        this.userRepository = userRepository;
        this.seasonRepository = seasonRepository;
        this.confrontationDetailService = confrontationDetailService;
    }

    @Override
    @Transactional
    public Long register(ConfrontationDTO dto) {
        var confrontation = new Confrontation();

        var formation = formationRepository.findById(dto.getFormationId()).orElseThrow(()-> new ObjectNotFoundException("Formação não encontrada"));
        List<FormationCharacterBuilt> formationCharacterBuilts = formationCharacterBuiltRepository.findByFormationId(formation.getId());
        String char1 = formationCharacterBuilts.get(0).getCharacterBuilt().getCharacter().getName();
        String char2 = formationCharacterBuilts.get(1).getCharacterBuilt().getCharacter().getName();
        String char3 = formationCharacterBuilts.get(2).getCharacterBuilt().getCharacter().getName();

        ConfrontationCharacterFormation characterFormation = createConfrontationCharacterFormation(char1, char2, char3);

        var user = userRepository.findById(dto.getUserId()).orElseThrow(()-> new ObjectNotFoundException("User não encontrado"));
        var season = seasonRepository.findById(dto.getSeasonId()).orElseThrow(()-> new ObjectNotFoundException("Temporada não encontrada"));

        confrontation.setDate(dto.getDate());
        confrontation.setDefeats(dto.getDefeats());
        confrontation.setLosses(dto.getLosses());
        confrontation.setWin(dto.isWin());
        confrontation.setConfrontationCharacterFormation(characterFormation);
        confrontation.setUser(user);
        confrontation.setFormation(formation);
        confrontation.setSeason(season);
        confrontationRepository.save(confrontation);

        confrontationDetailService.createDetails(confrontation);

        return confrontation.getId();
    }

    private ConfrontationCharacterFormation createConfrontationCharacterFormation(String char1, String char2, String char3) {
        ConfrontationCharacterFormation characterFormation = confrontationCharacterFormationRepository.findByCharacter1AndCharacter2AndCharacter3(char1, char2, char3);
        if(characterFormation==null){
            characterFormation = new ConfrontationCharacterFormation();
            characterFormation.setCharacter1(char1);
            characterFormation.setCharacter2(char2);
            characterFormation.setCharacter3(char3);
            confrontationCharacterFormationRepository.save(characterFormation);
        }
        return characterFormation;
    }
}
