package com.itschool.performance_tracker.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "games")
public class Game {

    @Column(name = "Game")
    private String game;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "Id")
    private Long id;

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
