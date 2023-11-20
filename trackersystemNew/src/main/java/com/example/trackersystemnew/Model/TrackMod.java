package com.example.trackersystemnew.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class TrackMod {

    @NotNull(message = "Cannot be ID null \n")
    @Size(min = 2 ,message = "please Enter Length more than 2 \n")
    private String ID;


    @NotNull(message = "\"please Not sat  title null\"")
    @Size(min = 8,message = "Length more than 8 \n")
    private String title;


    @NotNull(message = "Cannot be null \n")
    @Size(min = 15,message = "Length more than 15 \n")
    private String description;


    @NotNull(message = "Cannot be null \n")
    @Pattern(regexp = "^(?i)(Not Started|In Progress|Completed)$")
    private String status;


    @NotNull(message = "Cannot be null \n")
    @Size(min =6 ,message = "Length more than 6  \n")
    private String companyName;
}
