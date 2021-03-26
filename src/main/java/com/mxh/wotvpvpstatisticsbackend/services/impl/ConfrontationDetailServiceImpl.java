package com.mxh.wotvpvpstatisticsbackend.services.impl;

import com.mxh.wotvpvpstatisticsbackend.models.*;
import com.mxh.wotvpvpstatisticsbackend.repositories.*;
import com.mxh.wotvpvpstatisticsbackend.services.ConfrontationDetailService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ConfrontationDetailServiceImpl implements ConfrontationDetailService {

    ConfrontationDetailRepository confrontationDetailRepository;
    FormationCharacterBuiltRepository formationCharacterBuiltRepository;
    CharacterBuiltJobRepository characterBuiltJobRepository;
    CharacterJobConfrontationDetailRepository characterJobConfrontationDetailRepository;
    CharacterBuiltEquipmentRepository characterBuiltEquipmentRepository;
    EquipmentConfrontationDetailRepository equipmentConfrontationDetailRepository;
    CharacterBuiltSupportAbilityRepository characterBuiltSupportAbilityRepository;
    SupportAbilityConfrontationDetailRepository supportAbilityConfrontationDetailRepository;

    ConfrontationDetailServiceImpl(ConfrontationDetailRepository confrontationDetailRepository, FormationCharacterBuiltRepository formationCharacterBuiltRepository,
                                   CharacterBuiltJobRepository characterBuiltJobRepository, CharacterJobConfrontationDetailRepository characterJobConfrontationDetailRepository,
                                   CharacterBuiltEquipmentRepository characterBuiltEquipmentRepository, EquipmentConfrontationDetailRepository equipmentConfrontationDetailRepository,
                                   CharacterBuiltSupportAbilityRepository characterBuiltSupportAbilityRepository, SupportAbilityConfrontationDetailRepository supportAbilityConfrontationDetailRepository){
        this.confrontationDetailRepository = confrontationDetailRepository;
        this.formationCharacterBuiltRepository = formationCharacterBuiltRepository;
        this.characterBuiltJobRepository = characterBuiltJobRepository;
        this.characterJobConfrontationDetailRepository = characterJobConfrontationDetailRepository;
        this.characterBuiltEquipmentRepository = characterBuiltEquipmentRepository;
        this.equipmentConfrontationDetailRepository = equipmentConfrontationDetailRepository;
        this.characterBuiltSupportAbilityRepository = characterBuiltSupportAbilityRepository;
        this.supportAbilityConfrontationDetailRepository = supportAbilityConfrontationDetailRepository;
    }

    @Override
    @Transactional
    public void createDetails(Confrontation confrontation) {
        List<FormationCharacterBuilt> formationCharacterBuilts = formationCharacterBuiltRepository.findByFormationId(confrontation.getFormation().getId());
        formationCharacterBuilts.forEach(formationCharacterBuilt -> {
            var confrontationDetail = new ConfrontationDetail();
            confrontationDetail.setConfrontation(confrontation);
            var build = formationCharacterBuilt.getCharacterBuilt();
            confrontationDetail.setCharacter(build.getCharacter());
            confrontationDetail.setEsper(build.getEsper());
            confrontationDetail.setReaction(build.getReaction());
            confrontationDetail.setVisionCard(build.getVisionCard());
            confrontationDetail.setPosition(formationCharacterBuilt.getPosition());
            confrontationDetailRepository.save(confrontationDetail);

            saveCharacterJobsDetail(confrontationDetail, build);
            saveEquipmentDetail(confrontationDetail, build);
            saveSupportAbilityDetail(confrontationDetail, build);
        });

    }

    private void saveCharacterJobsDetail(ConfrontationDetail confrontationDetail, CharacterBuilt build) {
        List<CharacterBuiltJob> jobs = characterBuiltJobRepository.findByCharacterBuiltId(build.getId());
        jobs.forEach(job ->{
            var characterJobConfrontationDetail = new CharacterJobConfrontationDetail();
            characterJobConfrontationDetail.setCharacterJob(job.getCharacterJob());
            characterJobConfrontationDetail.setConfrontationDetail(confrontationDetail);
            characterJobConfrontationDetailRepository.save(characterJobConfrontationDetail);
        });
    }

    private void saveEquipmentDetail(ConfrontationDetail confrontationDetail, CharacterBuilt build) {
        List<CharacterBuiltEquipment> equipments = characterBuiltEquipmentRepository.findByCharacterBuiltId(build.getId());
        equipments.forEach(equipment ->{
            var equipmentConfrontationDetail = new EquipmentConfrontationDetail();
            equipmentConfrontationDetail.setEquipment(equipment.getEquipment());
            equipmentConfrontationDetail.setConfrontationDetail(confrontationDetail);
            equipmentConfrontationDetailRepository.save(equipmentConfrontationDetail);
        });
    }


    private void saveSupportAbilityDetail(ConfrontationDetail confrontationDetail, CharacterBuilt build) {
        List<CharacterBuiltSupportAbility> supportAbilities = characterBuiltSupportAbilityRepository.findByCharacterBuiltId(build.getId());
        supportAbilities.forEach(supportAbility -> {
            var supportAbilityConfrontationDetail = new SupportAbilityConfrontationDetail();
            supportAbilityConfrontationDetail.setSupportAbility(supportAbility.getSupportAbility());
            supportAbilityConfrontationDetail.setConfrontationDetail(confrontationDetail);
            supportAbilityConfrontationDetailRepository.save(supportAbilityConfrontationDetail);
        });
    }


}
