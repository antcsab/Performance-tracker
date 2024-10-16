package com.itschool.performance_tracker.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itschool.performance_tracker.entity.Player;
import com.itschool.performance_tracker.models.dtos.PlayerDTO;
import com.itschool.performance_tracker.models.dtos.RequestPlayerDTO;
import com.itschool.performance_tracker.models.dtos.ResponsePlayerDTO;
import com.itschool.performance_tracker.repository.PlayerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PlayerServiceImpl implements PlayerService {

    private final ObjectMapper objectMapper;
    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(ObjectMapper objectMapper, PlayerRepository playerRepository) {
        this.objectMapper = objectMapper;
        this.playerRepository = playerRepository;
    }


    @Override
    public ResponsePlayerDTO createPlayer(RequestPlayerDTO requestPlayerDTO) {
        Player playerEntity = objectMapper.convertValue(requestPlayerDTO, Player.class);
        Player playerEntityResponse = playerRepository.save(playerEntity);
        log.info("Player with id {} was saved", playerEntityResponse.getId());

        return objectMapper.convertValue(playerEntityResponse, ResponsePlayerDTO.class);
    }

    @Override
    public List<PlayerDTO> getPlayer() {
        List<Player> players = playerRepository.findAll();

        return players.stream()
                .map(Player -> objectMapper.convertValue(Player, PlayerDTO.class))
                .toList();
    }
}
