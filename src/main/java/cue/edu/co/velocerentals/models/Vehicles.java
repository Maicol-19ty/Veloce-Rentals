package cue.edu.co.velocerentals.models;

import cue.edu.co.velocerentals.utils.VehicleStatus;
import cue.edu.co.velocerentals.utils.VehicleType;
import lombok.*;

import java.time.Year;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder

public class Vehicles {
    private Integer vehicle_id;
    private VehicleType type;
    private String make;
    private String model;
    private Year year;
    private double price_per_day;
    private VehicleStatus status;
}

