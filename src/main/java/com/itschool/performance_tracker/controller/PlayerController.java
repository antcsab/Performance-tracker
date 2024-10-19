package com.itschool.performance_tracker.controller;


import com.itschool.performance_tracker.models.dtos.PlayerDTO;
import com.itschool.performance_tracker.models.dtos.RequestPlayerDTO;
import com.itschool.performance_tracker.models.dtos.ResponsePlayerDTO;
import com.itschool.performance_tracker.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/api/performance-tracker")
    public ResponseEntity<Void> deletePlayer(@RequestParam(value = "Id") Long Id) {
        playerService.deletePlayer(Id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/api/performance-tracker")
    public ResponseEntity<PlayerDTO> updatePlayer(@RequestParam(value = "Id") Long Id, @RequestBody PlayerDTO playerDTO) {
        return ResponseEntity.ok(playerService.updatePlayerById(Id, playerDTO));
    }
}
