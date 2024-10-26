package com.itschool.performance_tracker.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;


import java.util.List;

@Data
@Entity
@Table(name = "agents")
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


}
