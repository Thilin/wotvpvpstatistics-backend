package com.mxh.wotvpvpstatisticsbackend.services.impl;

import com.mxh.wotvpvpstatisticsbackend.dtos.ConfrontationDTO;
import com.mxh.wotvpvpstatisticsbackend.dtos.ConfrontationReportDTO;
import com.mxh.wotvpvpstatisticsbackend.dtos.ConfrontationTeamsDTO;
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
import java.util.ArrayList;
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
    CharacterRepository characterRepository;

    ConfrontationServiceImpl(ConfrontationRepository confrontationRepository, ConfrontationCharacterFormationRepository confrontationCharacterFormationRepository,
                             FormationRepository formationRepository, FormationCharacterBuiltRepository formationCharacterBuiltRepository,
                             UserRepository userRepository, SeasonRepository seasonRepository, ConfrontationDetailService confrontationDetailService,
                             CharacterRepository characterRepository){
        this.confrontationRepository = confrontationRepository;
        this.confrontationCharacterFormationRepository = confrontationCharacterFormationRepository;
        this.formationRepository = formationRepository;
        this.formationCharacterBuiltRepository = formationCharacterBuiltRepository;
        this.userRepository = userRepository;
        this.seasonRepository = seasonRepository;
        this.confrontationDetailService = confrontationDetailService;
        this.characterRepository = characterRepository;
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

    @Override
    public List<ConfrontationTeamsDTO> findAll() {
        List<ConfrontationCharacterFormation> teams = confrontationCharacterFormationRepository.findAll();
        List<ConfrontationTeamsDTO> teamsDTOS = new ArrayList<>();
        teams.forEach(team ->{
            var character1 = characterRepository.findByName(team.getCharacter1());
            var character2 = characterRepository.findByName(team.getCharacter2());
            var character3 = characterRepository.findByName(team.getCharacter3());
            var teamDTO = new ConfrontationTeamsDTO();
            teamDTO.setId(team.getId());
            teamDTO.setCharacter1(character1.getImage());
            teamDTO.setCharacter2(character2.getImage());
            teamDTO.setCharacter3(character3.getImage());
            teamsDTOS.add(teamDTO);
        });
        return teamsDTOS;
    }

    @Override
    public List<ConfrontationReportDTO> findAllBySeasonId(Long seasonId) {
        List<ConfrontationReportDTO> dtos = new ArrayList<>();
        List<Confrontation> confrontations = confrontationRepository.findBySeasonId(seasonId);
        confrontations.forEach(confrontation -> {
            var dto = new ConfrontationReportDTO();
            dto.setCharacter1(confrontation.getConfrontationCharacterFormation().getCharacter1());
            dto.setCharacter2(confrontation.getConfrontationCharacterFormation().getCharacter2());
            dto.setCharacter3(confrontation.getConfrontationCharacterFormation().getCharacter3());
            dto.setId(confrontation.getId());
            dto.setWin(confrontation.isWin());
            dtos.add(dto);
        });
        return dtos;
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
