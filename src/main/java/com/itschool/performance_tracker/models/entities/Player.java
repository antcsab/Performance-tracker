package com.itschool.performance_tracker.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "Id")
    private Long id;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "Position")
    private String position;

    @Column(name = "ContractValability")
    private LocalDate contractEnd;

    @Column(name = "Agent_Id")
    private Long agentId;

    // @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "agent_Id")

    //@ManyToMany
    //@JoinTable(name = "player_game",
    //       joinColumns = @JoinColumn(name = "Id"),
    //       inverseJoinColumns = {@JoinColumn(name = "Id")})

    // private Set<Game> game = new HashSet<>();


}

