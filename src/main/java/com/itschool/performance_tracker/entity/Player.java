package com.itschool.performance_tracker.entity;

import jakarta.persistence.*;

import java.time.LocalDate;


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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDate getContractEnd() {
        return contractEnd;
    }

    public void setContractEnd(LocalDate contractEnd) {
        this.contractEnd = contractEnd;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }
}

