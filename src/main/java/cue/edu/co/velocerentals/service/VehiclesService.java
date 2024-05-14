package cue.edu.co.velocerentals.service;

import cue.edu.co.velocerentals.enums.VehicleStatus;
import cue.edu.co.velocerentals.enums.VehicleType;

import java.util.List;

public interface VehiclesService<T> {

    // Method to list all vehicles
    List<T> listVehicles();

    // Method to list vehicles by status
    List<T> listVehiclesStatus(VehicleStatus status);

    // Method to list vehicles by type
    List<T> listVehiclesType(VehicleType type);

    // Method to list vehicles in ascending order of price
    List<T> listVehiclesPriceAsc();

    // Method to list vehicles in descending order of price
    List<T> listVehiclesPriceDesc();

}
