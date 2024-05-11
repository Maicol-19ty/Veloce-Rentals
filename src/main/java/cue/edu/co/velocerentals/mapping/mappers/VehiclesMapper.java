package cue.edu.co.velocerentals.mapping.mappers;

import cue.edu.co.velocerentals.mapping.DTO.VehiclesDTo;
import cue.edu.co.velocerentals.models.Vehicles;

// Mapper class for converting between Vehicles and VehiclesDTo objects.
public class VehiclesMapper {

    // Method to map from Vehicles model to VehiclesDTo DTO.
    public static VehiclesDTo mapFromModel(Vehicles vehicles) {
        return new VehiclesDTo(vehicles.getVehicle_id(), vehicles.getType(), vehicles.getMake(), vehicles.getModel(),
                vehicles.getYear(), vehicles.getPrice_per_day(), vehicles.getStatus());
    }

    // Method to map from VehiclesDTo DTO to Vehicles model.
    public static Vehicles mapFromDTO(VehiclesDTo vehiclesDTo) {
        return Vehicles.builder()
                .vehicle_id(vehiclesDTo.vehicle_id())
                .type(vehiclesDTo.type())
                .make(vehiclesDTo.make())
                .model(vehiclesDTo.model())
                .year(vehiclesDTo.year())
                .price_per_day(vehiclesDTo.price_per_day())
                .status(vehiclesDTo.status())
                .build();
    }

}
