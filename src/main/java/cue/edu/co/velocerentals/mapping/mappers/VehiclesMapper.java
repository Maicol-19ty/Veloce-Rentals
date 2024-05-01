package cue.edu.co.velocerentals.mapping.mappers;

import cue.edu.co.velocerentals.mapping.DTO.VehiclesDTo;
import cue.edu.co.velocerentals.models.Vehicles;

public class VehiclesMapper {

    public static VehiclesDTo mapFromModel(Vehicles vehicles) {
        return new VehiclesDTo(vehicles.getVehicle_id(), vehicles.getType(), vehicles.getMake(), vehicles.getModel(),
                vehicles.getYear(), vehicles.getPrice_per_day(), vehicles.getStatus());
    }

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
