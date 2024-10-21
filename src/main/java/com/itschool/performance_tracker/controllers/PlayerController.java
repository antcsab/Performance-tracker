package com.itschool.performance_tracker.controllers;


import com.itschool.performance_tracker.models.dtos.PlayerDTO;
import com.itschool.performance_tracker.models.dtos.RequestPlayerDTO;
import com.itschool.performance_tracker.models.dtos.ResponsePlayerDTO;
import com.itschool.performance_tracker.services.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/performance-tracker")
@RestController
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }


    @PostMapping()
    public ResponseEntity<ResponsePlayerDTO> createPlayer(@RequestBody RequestPlayerDTO requestPlayerDTO) {
        return ResponseEntity.ok(playerService.createPlayer(requestPlayerDTO));

    }

    @GetMapping()
    public ResponseEntity<List<PlayerDTO>> getPlayer() {
        return ResponseEntity.ok(playerService.getPlayer());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable(value = "id") Long id) {
        playerService.deletePlayer(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PlayerDTO> updatePlayer(@PathVariable(value = "id") Long id, @RequestBody PlayerDTO playerDTO) {
        return ResponseEntity.ok(playerService.updatePlayerById(id, playerDTO));
    }
}
