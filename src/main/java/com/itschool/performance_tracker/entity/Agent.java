package com.itschool.performance_tracker.entity;

import jakarta.persistence.*;
import lombok.Getter;


import java.util.List;

@Entity
@Table(name = "agent")
public class Agent {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "Id")
    private Long id;


    @Getter
    @Column(name = "Name")
    private String name;

    @JoinColumn(name = "agent_Id")

    @OneToMany(cascade = CascadeType.ALL)
    private List<Player> players;

    public List<Player> getPlayers() {
        return players;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
