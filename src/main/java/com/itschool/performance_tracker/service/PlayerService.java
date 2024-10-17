package com.itschool.performance_tracker.service;


import com.itschool.performance_tracker.models.dtos.PlayerDTO;
import com.itschool.performance_tracker.models.dtos.RequestPlayerDTO;
import com.itschool.performance_tracker.models.dtos.ResponsePlayerDTO;

import java.util.List;

public interface PlayerService {

    ResponsePlayerDTO createPlayer(RequestPlayerDTO requestPlayerDTO);

    List<PlayerDTO> getPlayer();

    void deletePlayer(Long Id);
}
