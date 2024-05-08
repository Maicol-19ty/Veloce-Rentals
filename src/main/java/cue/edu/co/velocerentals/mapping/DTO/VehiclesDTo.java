package cue.edu.co.velocerentals.mapping.DTO;

import cue.edu.co.velocerentals.enums.VehicleStatus;
import cue.edu.co.velocerentals.enums.VehicleType;

import java.time.Year;

public record VehiclesDTo(Integer vehicle_id, VehicleType type, String make,
                          String model, Year year, double price_per_day, VehicleStatus status) {
}
