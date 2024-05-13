package cue.edu.co.velocerentals.repository;

import cue.edu.co.velocerentals.enums.VehicleStatus;
import cue.edu.co.velocerentals.enums.VehicleType;

import java.util.List;

public interface VehiclesRepository <T> {

    List<T> listVehicles();
    List<T> listVehiclesStatus(VehicleStatus status);
    List<T> listVehiclesType(VehicleType type);
    List<T> listVehiclesPriceAsc();
    List<T> listVehiclesPriceDesc();

}
