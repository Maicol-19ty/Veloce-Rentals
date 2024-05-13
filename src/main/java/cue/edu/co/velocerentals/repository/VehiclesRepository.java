package cue.edu.co.velocerentals.repository;

import cue.edu.co.velocerentals.enums.VehicleStatus;
import cue.edu.co.velocerentals.enums.VehicleType;

import java.util.List;

public interface VehiclesRepository<T> {

    // Method to list all vehicles
    List<T> listVehicles();

    // Method to list vehicles with a specific status
    List<T> listVehiclesStatus(VehicleStatus status);

    // Method to list vehicles of a specific type
    List<T> listVehiclesType(VehicleType type);

    // Method to list vehicles sorted by price in ascending order
    List<T> listVehiclesPriceAsc();

    // Method to list vehicles sorted by price in descending order
    List<T> listVehiclesPriceDesc();

}
