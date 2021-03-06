package com.mxh.wotvpvpstatisticsbackend.services.impl;

import com.mxh.wotvpvpstatisticsbackend.dtos.*;
import com.mxh.wotvpvpstatisticsbackend.exceptions.ObjectNotFoundException;
import com.mxh.wotvpvpstatisticsbackend.models.CharacterBuilt;
import com.mxh.wotvpvpstatisticsbackend.models.CharacterBuiltEquipment;
import com.mxh.wotvpvpstatisticsbackend.models.CharacterBuiltJob;
import com.mxh.wotvpvpstatisticsbackend.models.CharacterBuiltSupportAbility;
import com.mxh.wotvpvpstatisticsbackend.repositories.*;
import com.mxh.wotvpvpstatisticsbackend.services.CharacterBuiltService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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
        this.characterBuiltSupportAbilityRepository = characterBuiltSupportAbilityRepository;
    }

    @Override
    @Transactional
    public Long create(CharacterBuiltCreateDTO dto) {
        var characterBuilt = new CharacterBuilt();
        var user = userRepository.findById(dto.getUserId()).orElseThrow(()-> new ObjectNotFoundException("Usu??rio n??o encontrado"));
        var esper = esperRepository.findById(dto.getEsperId()).orElseThrow(()-> new ObjectNotFoundException("Esper n??o encontrado"));
        var reaction = reactionRepository.findById(dto.getReactionId()).orElseThrow(()-> new ObjectNotFoundException("Reaction n??o encontrada"));
        var visionCard = visionCardRepository.findById(dto.getVisionCardId()).orElseThrow(()-> new ObjectNotFoundException("Vision card n??o encontrada"));
        var character = characterRepository.findById(dto.getCharacterId()).orElseThrow(()-> new ObjectNotFoundException("Personagem n??o encontrado"));
        characterBuilt.setUser(user);
        characterBuilt.setEsper(esper);
        characterBuilt.setReaction(reaction);
        characterBuilt.setVisionCard(visionCard);
        characterBuilt.setCharacter(character);
        characterBuilt.setName(dto.getName());
        characterBuiltRepository.save(characterBuilt);

        dto.getJobsId().forEach(jobId ->{
            var characterBuiltJob = new CharacterBuiltJob();
            var characterJob = characterJobRepository.findByCharacterIdAndJobId(character.getId(),jobId).orElseThrow(()-> new ObjectNotFoundException("Job n??o encontrado"));
            characterBuiltJob.setCharacterBuilt(characterBuilt);
            characterBuiltJob.setCharacterJob(characterJob);
            characterBuiltJob.setMain(characterJob.isMain());
            characterBuiltJobRepository.save(characterBuiltJob);
        });

        dto.getEquipmentsId().forEach(id ->{
            var characterBuiltEquipment = new CharacterBuiltEquipment();
            characterBuiltEquipment.setCharacterBuilt(characterBuilt);
            characterBuiltEquipment.setEquipment(equipmentRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Equipmento n??o encontrado")));
            characterBuiltEquipmentRepository.save(characterBuiltEquipment);
        });

        dto.getSupportAbilitiesId().forEach(supportId->{
            var characterBuiltSupportAbility = new CharacterBuiltSupportAbility();
            characterBuiltSupportAbility.setCharacterBuilt(characterBuilt);
            characterBuiltSupportAbility.setSupportAbility(supportAbilityRepository.findById(supportId).orElseThrow(()->new ObjectNotFoundException("Support Ability n??o encontrada")));
            characterBuiltSupportAbilityRepository.save(characterBuiltSupportAbility);
        });


        return characterBuilt.getId();
    }

    @Override
    public CharacterBuiltResponseDTO findById(Long id) {
        var characterBuilt = characterBuiltRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Characterbuilt n??o encontrado"));
        var dto = new CharacterBuiltResponseDTO();
        dto.setId(characterBuilt.getId());
        dto.setName(characterBuilt.getName());
        dto.setCharacter(characterBuilt.getCharacter().getName());
        dto.setEsper(characterBuilt.getEsper().getName());
        dto.setVisionCard(characterBuilt.getVisionCard().getName());
        dto.setReaction(characterBuilt.getReaction().getDescription());

        List<CharacterBuiltJob> characterBuiltJobs = characterBuiltJobRepository.findByCharacterBuiltId(characterBuilt.getId());
        List<JobResponseDTO> jobs = new ArrayList<>();
        characterBuiltJobs.forEach(characterBuiltJob -> {
            JobResponseDTO job = getJobResponseDTO(characterBuiltJob);
            jobs.add(job);
        });
        dto.setJobs(jobs);

        List<CharacterBuiltEquipment> equipments = characterBuiltEquipmentRepository.findByCharacterBuiltId(characterBuilt.getId());
        List<EquipmentResponseDTO> equipmentDTOS = new ArrayList<>();
        equipments.forEach(characterBuiltEquipment -> {
            EquipmentResponseDTO equipmentDTO = getEquipmentResponseDTO(characterBuiltEquipment);
            equipmentDTOS.add(equipmentDTO);
        });
        dto.setEquipments(equipmentDTOS);

        List<CharacterBuiltSupportAbility> characterBuiltSupportAbilities = characterBuiltSupportAbilityRepository.findByCharacterBuiltId(characterBuilt.getId());
        if(characterBuiltSupportAbilities == null)
            dto.setSupportAbilities(null);
        else{
            List<SupportAbilityResponseDTO> supportAbilityResponseDTOS = new ArrayList<>();
            characterBuiltSupportAbilities.forEach(characterBuiltSupportAbility ->{
                SupportAbilityResponseDTO supportAbilityDTO = getSupportAbilityResponseDTO(characterBuiltSupportAbility);
                supportAbilityResponseDTOS.add(supportAbilityDTO);
            });
            dto.setSupportAbilities(supportAbilityResponseDTOS);
        }
        return dto;
    }

    @Override
    public List<CharacterBuiltResponseDTO> findAllByUserId(Long id) {
        List<CharacterBuiltResponseDTO> responseListDTO = new ArrayList<>();
        List<CharacterBuilt> builds = characterBuiltRepository.findByUserId(id);
        if(builds == null)
            return responseListDTO;
        else{
            builds.forEach(characterBuilt -> {
                var dto = new CharacterBuiltResponseDTO();
                dto.setId(characterBuilt.getId());
                dto.setName(characterBuilt.getName());
                dto.setCharacter(characterBuilt.getCharacter().getName());
                dto.setEsper(characterBuilt.getEsper().getName());
                dto.setVisionCard(characterBuilt.getVisionCard().getName());
                dto.setReaction(characterBuilt.getReaction().getDescription());

                List<CharacterBuiltJob> characterBuiltJobs = characterBuiltJobRepository.findByCharacterBuiltId(characterBuilt.getId());
                List<JobResponseDTO> jobs = new ArrayList<>();
                characterBuiltJobs.forEach(characterBuiltJob -> {
                    JobResponseDTO job = getJobResponseDTO(characterBuiltJob);
                    jobs.add(job);
                });
                dto.setJobs(jobs);

                List<CharacterBuiltEquipment> equipments = characterBuiltEquipmentRepository.findByCharacterBuiltId(characterBuilt.getId());
                List<EquipmentResponseDTO> equipmentDTOS = new ArrayList<>();
                equipments.forEach(characterBuiltEquipment -> {
                    EquipmentResponseDTO equipmentDTO = getEquipmentResponseDTO(characterBuiltEquipment);
                    equipmentDTOS.add(equipmentDTO);
                });
                dto.setEquipments(equipmentDTOS);

                List<CharacterBuiltSupportAbility> characterBuiltSupportAbilities = characterBuiltSupportAbilityRepository.findByCharacterBuiltId(characterBuilt.getId());
                if(characterBuiltSupportAbilities == null)
                    dto.setSupportAbilities(null);
                else{
                    List<SupportAbilityResponseDTO> supportAbilityResponseDTOS = new ArrayList<>();
                    characterBuiltSupportAbilities.forEach(characterBuiltSupportAbility ->{
                        SupportAbilityResponseDTO supportAbilityDTO = getSupportAbilityResponseDTO(characterBuiltSupportAbility);
                        supportAbilityResponseDTOS.add(supportAbilityDTO);
                    });
                    dto.setSupportAbilities(supportAbilityResponseDTOS);
                }
                responseListDTO.add(dto);
            });
            return responseListDTO;
        }
    }

    @Override
    public void update(Long userId, CharacterBuiltCreateDTO dto) {
        var user = userRepository.findById(userId).orElseThrow(() -> new ObjectNotFoundException("Usu??rio n??o encontrado"));
        var build = characterBuiltRepository.findById(dto.getUserId()).orElseThrow(() -> new ObjectNotFoundException("Build n??o encontrada"));
        if(build.getUser() != user){
            throw new ObjectNotFoundException("User ?? diferente");
        }
        else{
            var esper = esperRepository.findById(dto.getEsperId()).orElseThrow(()-> new ObjectNotFoundException("Esper n??o encontrado"));
            var reaction = reactionRepository.findById(dto.getReactionId()).orElseThrow(()-> new ObjectNotFoundException("Reaction n??o encontrada"));
            var visionCard = visionCardRepository.findById(dto.getVisionCardId()).orElseThrow(()-> new ObjectNotFoundException("Vision card n??o encontrada"));
            var character = characterRepository.findById(dto.getCharacterId()).orElseThrow(()-> new ObjectNotFoundException("Personagem n??o encontrado"));
            build.setUser(user);
            build.setEsper(esper);
            build.setReaction(reaction);
            build.setVisionCard(visionCard);
            build.setCharacter(character);
            build.setName(dto.getName());
            characterBuiltRepository.save(build);

            dto.getJobsId().forEach(jobId ->{
                var characterBuiltJob = new CharacterBuiltJob();
                var characterJob = characterJobRepository.findByCharacterIdAndJobId(character.getId(),jobId).orElseThrow(()-> new ObjectNotFoundException("Job n??o encontrado"));
                characterBuiltJob.setCharacterBuilt(build);
                characterBuiltJob.setCharacterJob(characterJob);
                characterBuiltJob.setMain(characterJob.isMain());
                characterBuiltJobRepository.save(characterBuiltJob);
            });

            dto.getEquipmentsId().forEach(id ->{
                var characterBuiltEquipment = new CharacterBuiltEquipment();
                characterBuiltEquipment.setCharacterBuilt(build);
                characterBuiltEquipment.setEquipment(equipmentRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Equipmento n??o encontrado")));
                characterBuiltEquipmentRepository.save(characterBuiltEquipment);
            });

            dto.getSupportAbilitiesId().forEach(supportId->{
                var characterBuiltSupportAbility = new CharacterBuiltSupportAbility();
                characterBuiltSupportAbility.setCharacterBuilt(build);
                characterBuiltSupportAbility.setSupportAbility(supportAbilityRepository.findById(supportId).orElseThrow(()->new ObjectNotFoundException("Support Ability n??o encontrada")));
                characterBuiltSupportAbilityRepository.save(characterBuiltSupportAbility);
            });
        }
    }

    private SupportAbilityResponseDTO getSupportAbilityResponseDTO(CharacterBuiltSupportAbility characterBuiltSupportAbility) {
        var supportAbilityDTO = new SupportAbilityResponseDTO();
        supportAbilityDTO.setId(characterBuiltSupportAbility.getSupportAbility().getId());
        supportAbilityDTO.setDescription(characterBuiltSupportAbility.getSupportAbility().getDescription());
        return supportAbilityDTO;
    }

    private EquipmentResponseDTO getEquipmentResponseDTO(CharacterBuiltEquipment characterBuiltEquipment) {
        var equipment = characterBuiltEquipment.getEquipment();
        var equipmentDTO = new EquipmentResponseDTO();
        equipmentDTO.setId(equipment.getId());
        equipmentDTO.setName(equipment.getName());
        equipmentDTO.setCategory(equipment.getEquipmentCategory().getDescription());
        equipmentDTO.setType(equipment.getEquipmentType().getDescription());
        equipmentDTO.setRarity(equipment.getRarity());
        equipmentDTO.setTMR(equipment.isTMR());
        return equipmentDTO;
    }

    private JobResponseDTO getJobResponseDTO(CharacterBuiltJob characterBuiltJob) {
        var job = new JobResponseDTO();
        job.setId(characterBuiltJob.getCharacterJob().getJob().getId());
        job.setDescription(characterBuiltJob.getCharacterJob().getJob().getDescription());
        job.setImage(characterBuiltJob.getCharacterJob().getJob().getImage());
        return job;
    }
}
