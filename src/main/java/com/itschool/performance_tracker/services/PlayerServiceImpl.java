package com.itschool.performance_tracker.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itschool.performance_tracker.models.entities.Player;
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
    public void deletePlayer(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Player Id cannot be null.");
        }
        Player existingPackage = playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException(id));

        playerRepository.delete(existingPackage);
        log.info("Player with Id {} was deleted", id);
    }

    @Override
    public PlayerDTO updatePlayerById(Long id, PlayerDTO playerDTO) {
        if (id == null) {
            throw new IllegalArgumentException("Player id cannot be null.");
        }
        Player existingPlayer = playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException(id));

        updateExistingPlayer(existingPlayer, playerDTO);
        Player updatePlayer = playerRepository.save(existingPlayer);
        log.info("Player with id {} was updated", existingPlayer.getId());

        return objectMapper.convertValue(updatePlayer, PlayerDTO.class);
    }

    private void updateExistingPlayer(Player existingPlayer, PlayerDTO playerDTO) {
        existingPlayer.setFirstName(playerDTO.getFirstName());
        existingPlayer.setLastName(playerDTO.getLastName());
        existingPlayer.setAgentId(playerDTO.getAgentId());
        existingPlayer.setContractEnd(playerDTO.getContractEnd());
        existingPlayer.setPosition(playerDTO.getPosition());
    }
}
