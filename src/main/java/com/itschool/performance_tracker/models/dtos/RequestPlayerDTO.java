package com.itschool.performance_tracker.models.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RequestPlayerDTO {

    @NotBlank(message = "This field is mandatory")
    private Long id;

    @NotBlank(message = "This field is mandatory")
    private String firstName;

    @NotBlank(message = "This field is mandatory")
    private String lastName;

    private String position;

    private LocalDate contractEnd;

    private Long agentId;
}
