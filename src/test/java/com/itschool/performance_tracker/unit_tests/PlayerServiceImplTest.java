package com.itschool.performance_tracker.unit_tests;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import com.itschool.performance_tracker.services.PlayerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itschool.performance_tracker.models.dtos.PlayerDTO;
import com.itschool.performance_tracker.models.dtos.RequestPlayerDTO;
import com.itschool.performance_tracker.models.dtos.ResponsePlayerDTO;
import com.itschool.performance_tracker.models.entities.Player;
import com.itschool.performance_tracker.repository.PlayerRepository;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceImplTest {

    @Mock
    private ObjectMapper mockObjectMapper;

    @Mock
    private PlayerRepository mockRepository;

    @InjectMocks
    private PlayerServiceImpl playerService;

    @Test
    public void testCreatePlayer() {
        RequestPlayerDTO requestPlayer = createPlayerRequest();
        ResponsePlayerDTO responsePlayer = createPlayerResponse();
        Player playerEntity = createPlayerEntity();

        when(mockObjectMapper.convertValue(requestPlayer, Player.class)).thenReturn(playerEntity);
        when(mockRepository.save(playerEntity)).thenReturn(playerEntity);
        when(mockObjectMapper.convertValue(playerEntity, ResponsePlayerDTO.class)).thenReturn(responsePlayer);

        ResponsePlayerDTO savedPlayer = playerService.createPlayer(requestPlayer);

        verify(mockRepository).save(playerEntity);

        assertEquals(responsePlayer, savedPlayer);
    }

    @Test
    public void testUpdatePlayerById() {
        PlayerDTO requestPlayer = createPlayerDTO();
        PlayerDTO responsePlayer = createPlayerDTO();

        Player playerEntity = new Player();
        when(mockRepository.findById(1L)).thenReturn(Optional.of(playerEntity));
        when(mockRepository.save(playerEntity)).thenReturn(playerEntity);
        when(mockObjectMapper.convertValue(playerEntity, PlayerDTO.class)).thenReturn(responsePlayer);

        PlayerDTO savedPlayer = playerService.updatePlayerById(1L, requestPlayer);

        verify(mockRepository).save(playerEntity);

        assertEquals(responsePlayer, savedPlayer);
    }

    private Player createPlayerEntity() {
        Player playerEntity = new Player();
        playerEntity.setAgentId(1L);
        playerEntity.setContractEnd(LocalDate.EPOCH);
        playerEntity.setFirstName("Csaba");
        playerEntity.setLastName("Antal");
        playerEntity.setId(1L);
        playerEntity.setPosition("forward");
        return playerEntity;
    }

    private ResponsePlayerDTO createPlayerResponse() {
        ResponsePlayerDTO responsePlayer = new ResponsePlayerDTO();
        responsePlayer.setAgentId(1L);
        responsePlayer.setContractEnd(LocalDate.EPOCH);
        responsePlayer.setFirstName("Csaba");
        responsePlayer.setLastName("Antal");
        responsePlayer.setId(1L);
        responsePlayer.setPosition("forward");
        return responsePlayer;
    }

    private RequestPlayerDTO createPlayerRequest() {
        RequestPlayerDTO requestPlayer = new RequestPlayerDTO();
        requestPlayer.setAgentId(1L);
        requestPlayer.setContractEnd(LocalDate.EPOCH);
        requestPlayer.setFirstName("Csaba");
        requestPlayer.setLastName("Antal");
        requestPlayer.setId(1L);
        requestPlayer.setPosition("forward");
        return requestPlayer;
    }

    private PlayerDTO createPlayerDTO() {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setAgentId(1L);
        playerDTO.setContractEnd(LocalDate.EPOCH);
        playerDTO.setFirstName("Csaba");
        playerDTO.setLastName("Antal");
        playerDTO.setId(1L);
        playerDTO.setPosition("forward");
        return playerDTO;
    }
}
