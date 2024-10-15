package com.itschool.performance_tracker.controller;


import com.itschool.performance_tracker.models.dtos.PlayerDTO;
import com.itschool.performance_tracker.models.dtos.RequestPlayerDTO;
import com.itschool.performance_tracker.models.dtos.ResponsePlayerDTO;
import com.itschool.performance_tracker.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }


    @PostMapping("/api/performance-tracker")
    public ResponseEntity<ResponsePlayerDTO> createPlayer(@RequestBody RequestPlayerDTO requestPlayerDTO) {
        return ResponseEntity.ok(playerService.createPlayer(requestPlayerDTO));

    }

    @GetMapping("/api/performance-tracker")
    public ResponseEntity<List<PlayerDTO>> getPlayer() {
        return ResponseEntity.ok(playerService.getPlayer());
    }
}
