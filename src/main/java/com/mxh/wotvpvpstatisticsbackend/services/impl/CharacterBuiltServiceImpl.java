package com.mxh.wotvpvpstatisticsbackend.services.impl;

import com.mxh.wotvpvpstatisticsbackend.dtos.CharacterBuiltCreateDTO;
import com.mxh.wotvpvpstatisticsbackend.exceptions.ObjectNotFoundException;
import com.mxh.wotvpvpstatisticsbackend.models.CharacterBuilt;
import com.mxh.wotvpvpstatisticsbackend.models.CharacterBuiltEquipment;
import com.mxh.wotvpvpstatisticsbackend.models.CharacterBuiltJob;
import com.mxh.wotvpvpstatisticsbackend.models.CharacterBuiltSupportAbility;
import com.mxh.wotvpvpstatisticsbackend.repositories.*;
import com.mxh.wotvpvpstatisticsbackend.services.CharacterBuiltService;
import org.springframework.stereotype.Service;

@Service
public class CharacterBuiltServiceImpl implements CharacterBuiltService {

    CharacterBuiltRepository characterBuiltRepository;
    UserRepository userRepository;
    EsperRepository esperRepository;
    ReactionRepository reactionRepository;
    VisionCardRepository visionCardRepository;
    CharacterRepository characterRepository;
    CharacterJobRepository characterJobRepository;
    CharacterBuiltJobRepository characterBuiltJobRepository;
    EquipmentRepository equipmentRepository;
    CharacterBuiltEquipmentRepository characterBuiltEquipmentRepository;
    SupportAbilityRepository supportAbilityRepository;
    CharacterBuiltSupportAbilityRepository characterBuiltSupportAbilityRepository;

    CharacterBuiltServiceImpl(CharacterBuiltRepository characterBuiltRepository, UserRepository userRepository,
                              EsperRepository esperRepository, ReactionRepository reactionRepository,
                              VisionCardRepository visionCardRepository, CharacterRepository characterRepository,
                              CharacterJobRepository characterJobRepository, CharacterBuiltJobRepository characterBuiltJobRepository,
                              EquipmentRepository equipmentRepository, CharacterBuiltEquipmentRepository characterBuiltEquipmentRepository,
                              SupportAbilityRepository supportAbilityRepository, CharacterBuiltSupportAbilityRepository characterBuiltSupportAbilityRepository){

        this.characterBuiltRepository = characterBuiltRepository;
        this.userRepository = userRepository;
        this.esperRepository = esperRepository;
        this.reactionRepository = reactionRepository;
        this.visionCardRepository = visionCardRepository;
        this.characterRepository = characterRepository;
        this.characterJobRepository = characterJobRepository;
        this.characterBuiltJobRepository = characterBuiltJobRepository;
        this.equipmentRepository = equipmentRepository;
        this.characterBuiltEquipmentRepository = characterBuiltEquipmentRepository;
        this.supportAbilityRepository = supportAbilityRepository;
        this.supportAbilityRepository = supportAbilityRepository;
    }

    @Override
    public Long create(CharacterBuiltCreateDTO dto) {
        var characterBuilt = new CharacterBuilt();
        var user = userRepository.findById(dto.getUserId()).orElseThrow(()-> new ObjectNotFoundException("Usuário não encontrado"));
        var esper = esperRepository.findById(dto.getEsperId()).orElseThrow(()-> new ObjectNotFoundException("Esper não encontrado"));
        var reaction = reactionRepository.findById(dto.getReactionId()).orElseThrow(()-> new ObjectNotFoundException("Reaction não encontrada"));
        var visionCard = visionCardRepository.findById(dto.getVisionCardId()).orElseThrow(()-> new ObjectNotFoundException("Vision card não encontrada"));
        var character = characterRepository.findById(dto.getCharacterId()).orElseThrow(()-> new ObjectNotFoundException("Personagem não encontrado"));
        characterBuilt.setUser(user);
        characterBuilt.setEsper(esper);
        characterBuilt.setReaction(reaction);
        characterBuilt.setVisionCard(visionCard);
        characterBuilt.setCharacter(character);
        characterBuilt.setName(dto.getName());
        characterBuiltRepository.save(characterBuilt);

        dto.getJobsId().forEach(jobId ->{
            var characterBuiltJob = new CharacterBuiltJob();
            var characterJob = characterJobRepository.findByCharacterIdAndJobId(character.getId(),jobId).orElseThrow(()-> new ObjectNotFoundException("Job não encontrado"));
            characterBuiltJob.setCharacterBuilt(characterBuilt);
            characterBuiltJob.setCharacterJob(characterJob);
            characterBuiltJob.setMain(characterJob.isMain());
            characterBuiltJobRepository.save(characterBuiltJob);
        });

        dto.getEquipmentsDTO().forEach(equipmentDTO ->{
            var characterBuiltEquipment = new CharacterBuiltEquipment();
            characterBuiltEquipment.setPlus(equipmentDTO.getPlus());
            characterBuiltEquipment.setCharacterBuilt(characterBuilt);
            characterBuiltEquipment.setEquipment(equipmentRepository.findById(equipmentDTO.getEquipmentId()).orElseThrow(()-> new ObjectNotFoundException("Equipmento não encontrado")));
            characterBuiltEquipmentRepository.save(characterBuiltEquipment);
        });

        dto.getSupportAbilitiesId().forEach(supportId->{
            var characterBuiltSupportAbility = new CharacterBuiltSupportAbility();
            characterBuiltSupportAbility.setCharacterBuilt(characterBuilt);
            characterBuiltSupportAbility.setSupportAbility(supportAbilityRepository.findById(supportId).orElseThrow(()->new ObjectNotFoundException("Support Ability não encontrada")));
            characterBuiltSupportAbilityRepository.save(characterBuiltSupportAbility);
        });


        return characterBuilt.getId();
    }
}
