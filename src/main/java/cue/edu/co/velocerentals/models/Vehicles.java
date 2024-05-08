package cue.edu.co.velocerentals.models;

import cue.edu.co.velocerentals.enums.VehicleStatus;
import cue.edu.co.velocerentals.enums.VehicleType;
import jakarta.enterprise.context.SessionScoped;
import lombok.*;

import java.io.Serializable;
import java.time.Year;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
@SessionScoped

public class Vehicles implements Serializable {
    private Integer vehicle_id;
    private VehicleType type;
    private String make;
    private String model;
    private Year year;
    private double price_per_day;
    private VehicleStatus status;
}

