package com.example.eventsystemnew.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class EvMod {

    @NotNull(message = "Cannot be id null!! \n")
    @Size(min = 2,message = "Should be ID Length more than 2 \n")
    private String id;


    @NotNull(message = "Cannot be description null!! \n")
    @Size(min = 2,message = "Should be description Length more than 2 \n")

    private String description;


    @NotNull(message = "Capacity cannot be null")
    @Digits(integer = 5, fraction = 0, message = "Capacity must be a valid number")
    @Min(value = 26, message = "Capacity must be more than 25")
    private int capacity;




    @NotNull(message = "startDate cannot be null")
    @PastOrPresent(message = "startDate must be in the past or present")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime startDate = LocalDateTime.now();



    @NotNull(message = "startDate cannot be null")
    @PastOrPresent(message = "startDate must be in the past or present")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime endDate = LocalDateTime.now().plusDays(90);



}
