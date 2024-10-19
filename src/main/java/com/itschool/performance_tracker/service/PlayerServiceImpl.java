package com.itschool.performance_tracker.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itschool.performance_tracker.entity.Player;
import com.itschool.performance_tracker.exceptions.PlayerNotFoundException;
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

    @Override
    public void deletePlayer(Long Id) {
        if (Id == null) {
            throw new IllegalArgumentException("Player ID cannot be null.");
        }
        Player existingPackage = playerRepository.findById(Id)
                .orElseThrow(() -> new PlayerNotFoundException(Id));

        playerRepository.delete(existingPackage);
        log.info("Player with Id {} was deleted", Id);
    }

    public PlayerDTO updatePlayerById(Long Id, PlayerDTO playerDTO) {
        if (Id == null) {
            throw new IllegalArgumentException("Player Id cannot be null.");
        }
        Player existingPlayer = playerRepository.findById(Id)
                .orElseThrow(() -> new PlayerNotFoundException(Id));

        updateExistingPlayer(existingPlayer, playerDTO);
        Player updatePlayer = playerRepository.save(existingPlayer);
        log.info("Player with Id {} was updated", existingPlayer.getId());

        return objectMapper.convertValue(updatePlayer, PlayerDTO.class);
    }

    private void updateExistingPlayer(Player existingPlayer, PlayerDTO playerDTO) {
        existingPlayer.getFirstName(playerDTO.getFirstName());
        existingPlayer.getLastName(playerDTO.getLastName());
        existingPlayer.getAgentId(playerDTO.getAgentId());
        existingPlayer.getContractEnd(playerDTO.getContractEnd());
        existingPlayer.getPosition(playerDTO.getPosition());
    }
}
