package com.itschool.performance_tracker.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "games")
public class Game {

    @Column(name = "Game")
    private String game;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "Id")
    private Long id;

}
