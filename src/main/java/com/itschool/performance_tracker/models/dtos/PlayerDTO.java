package com.itschool.performance_tracker.models.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PlayerDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String position;

    private LocalDate contractEnd;

    private Long agentId;

}
