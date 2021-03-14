package com.mxh.wotvpvpstatisticsbackend.services.impl;

import com.mxh.wotvpvpstatisticsbackend.dtos.CharacterBuiltResumeDTO;
import com.mxh.wotvpvpstatisticsbackend.dtos.EquipmentResumeDTO;
import com.mxh.wotvpvpstatisticsbackend.dtos.FormationCreateDTO;
import com.mxh.wotvpvpstatisticsbackend.dtos.FormationResponseDTO;
import com.mxh.wotvpvpstatisticsbackend.exceptions.ObjectNotFoundException;
import com.mxh.wotvpvpstatisticsbackend.models.CharacterBuiltEquipment;
import com.mxh.wotvpvpstatisticsbackend.models.Formation;
import com.mxh.wotvpvpstatisticsbackend.models.FormationCharacterBuilt;
import com.mxh.wotvpvpstatisticsbackend.repositories.*;
import com.mxh.wotvpvpstatisticsbackend.services.FormationService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class FormationServiceImpl implements FormationService {

    FormationRepository formationRepository;
    FormationCharacterBuiltRepository formationCharacterBuiltRepository;
    UserRepository userRepository;
    CharacterBuiltRepository characterBuiltRepository;
    CharacterBuiltEquipmentRepository characterBuiltEquipmentRepository;

    FormationServiceImpl(FormationRepository formationRepository, FormationCharacterBuiltRepository formationCharacterBuiltRepository,
                         UserRepository userRepository, CharacterBuiltRepository characterBuiltRepository,
                         CharacterBuiltEquipmentRepository characterBuiltEquipmentRepository){
        this.formationRepository=formationRepository;
        this.formationCharacterBuiltRepository=formationCharacterBuiltRepository;
        this.userRepository = userRepository;
        this.characterBuiltRepository = characterBuiltRepository;
        this.characterBuiltEquipmentRepository = characterBuiltEquipmentRepository;
    }
    @Override
    @Transactional
    public Long create(FormationCreateDTO dto) {
        var formation = new Formation();
        var formationCharacterBuilt = new FormationCharacterBuilt();
        var user = userRepository.findById(dto.getUserId()).orElseThrow(()-> new ObjectNotFoundException("Usuário não encontrado"));

        formation.setAttack(true);
        formation.setUser(user);
        formation.setName(dto.getName());
        formationRepository.save(formation);

        formationCharacterBuilt.setFormation(formation);
        dto.getCharacters().forEach(characterFormation->{
            var characterBuilt = characterBuiltRepository.findById(characterFormation.getCharacterBuiltId()).orElseThrow(()-> new ObjectNotFoundException("Build não encontrada"));
            formationCharacterBuilt.setCharacterBuilt(characterBuilt);
            formationCharacterBuilt.setOrder(characterFormation.getOrder());
            formationCharacterBuiltRepository.save(formationCharacterBuilt);
        });
        return formation.getId();
    }

    @Override
    public FormationResponseDTO findById(Long id) {
        var formationResponseDTO = new FormationResponseDTO();
        var formation = formationRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Formação não encontrada"));
        formationResponseDTO.setName(formation.getName());
        formationResponseDTO.setId(formation.getId());

        List<CharacterBuiltResumeDTO>characterBuilts = new ArrayList<>();
        List<FormationCharacterBuilt> formationCharacterBuilts = formationCharacterBuiltRepository.findByFormationId(formation.getId());
        formationCharacterBuilts.forEach(build ->{
            var buildDTO = new CharacterBuiltResumeDTO();
            buildDTO.setId(build.getId());
            buildDTO.setName(build.getCharacterBuilt().getCharacter().getName());
            buildDTO.setEsper(build.getCharacterBuilt().getEsper().getName());
            buildDTO.setVisionCard(build.getCharacterBuilt().getVisionCard().getName());

            List<EquipmentResumeDTO> equipmentsDTO = new ArrayList<>();
            List<CharacterBuiltEquipment> characterBuiltEquipments = characterBuiltEquipmentRepository.findByCharacterBuiltId(build.getCharacterBuilt().getId());
            characterBuiltEquipments.forEach(equipment ->{
                var equipmentDTO = new EquipmentResumeDTO();
                equipmentDTO.setName(equipment.getEquipment().getName());
                equipmentDTO.setImage(equipment.getEquipment().getImage());
                equipmentDTO.setId(equipment.getEquipment().getId());
                equipmentsDTO.add(equipmentDTO);
            });
            characterBuilts.add(buildDTO);
        });
        formationResponseDTO.setCharacters(characterBuilts);
        return formationResponseDTO;
    }

    @Override
    public List<FormationResponseDTO> findByUserId(Long id) {
        List<FormationResponseDTO> formationsDTO = new ArrayList<>();
        List<Formation> formations = formationRepository.findByUserId(id);
        formations.forEach(formation -> {
            var formationResponseDTO = new FormationResponseDTO();
            formationResponseDTO.setName(formation.getName());
            formationResponseDTO.setId(formation.getId());

            List<CharacterBuiltResumeDTO>characterBuilts = new ArrayList<>();
            List<FormationCharacterBuilt> formationCharacterBuilts = formationCharacterBuiltRepository.findByFormationId(formation.getId());
            formationCharacterBuilts.forEach(build ->{
                var buildDTO = new CharacterBuiltResumeDTO();
                buildDTO.setId(build.getId());
                buildDTO.setName(build.getCharacterBuilt().getCharacter().getName());
                buildDTO.setEsper(build.getCharacterBuilt().getEsper().getName());
                buildDTO.setVisionCard(build.getCharacterBuilt().getVisionCard().getName());

                List<EquipmentResumeDTO> equipmentsDTO = new ArrayList<>();
                List<CharacterBuiltEquipment> characterBuiltEquipments = characterBuiltEquipmentRepository.findByCharacterBuiltId(build.getCharacterBuilt().getId());
                characterBuiltEquipments.forEach(equipment ->{
                    var equipmentDTO = new EquipmentResumeDTO();
                    equipmentDTO.setName(equipment.getEquipment().getName());
                    equipmentDTO.setImage(equipment.getEquipment().getImage());
                    equipmentDTO.setId(equipment.getEquipment().getId());
                    equipmentsDTO.add(equipmentDTO);
                });
                characterBuilts.add(buildDTO);
            });
            formationResponseDTO.setCharacters(characterBuilts);
            formationsDTO.add(formationResponseDTO);
        });
        return formationsDTO;
    }
}
