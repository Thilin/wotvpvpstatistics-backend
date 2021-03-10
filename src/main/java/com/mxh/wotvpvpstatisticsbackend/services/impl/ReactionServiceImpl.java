package com.mxh.wotvpvpstatisticsbackend.services.impl;

import com.mxh.wotvpvpstatisticsbackend.dtos.ReactionResponseDTO;
import com.mxh.wotvpvpstatisticsbackend.exceptions.ObjectNotFoundException;
import com.mxh.wotvpvpstatisticsbackend.repositories.ReactionRepository;
import com.mxh.wotvpvpstatisticsbackend.services.ReactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReactionServiceImpl implements ReactionService {

    private ReactionRepository reactionRepository;

    ReactionServiceImpl(ReactionRepository reactionRepository){
        this.reactionRepository = reactionRepository;
    }
    @Override
    public ReactionResponseDTO findById(Long id) {
        var reaction = reactionRepository.findById(id).orElseThrow(()->new ObjectNotFoundException("Reaction não encontrada"));
        var dto = new ReactionResponseDTO();
        dto.setId(reaction.getId());
        dto.setDescription(reaction.getDescription());
        dto.setJobId(reaction.getJob().getId());
        dto.setJobDescription(reaction.getJob().getDescription());
        dto.setImage(reaction.getJob().getImage());
        return dto;
    }

    @Override
    public List<ReactionResponseDTO> findAll() {
        return reactionRepository.findALlReactions();
    }
}
