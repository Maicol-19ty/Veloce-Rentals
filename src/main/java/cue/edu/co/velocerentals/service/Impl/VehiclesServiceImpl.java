package cue.edu.co.velocerentals.service.Impl;

import cue.edu.co.velocerentals.enums.VehicleStatus;
import cue.edu.co.velocerentals.enums.VehicleType;
import cue.edu.co.velocerentals.models.Vehicles;
import cue.edu.co.velocerentals.repository.VehiclesRepository;
import cue.edu.co.velocerentals.service.VehiclesService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class VehiclesServiceImpl implements VehiclesService<Vehicles> {

    @Inject
    private VehiclesRepository<Vehicles> vehicleRepository;

    // Setter method for VehiclesRepository, enabling dependency injection
    public void setVehicleRepository(VehiclesRepository<Vehicles> repo) {
        this.vehicleRepository = repo;
    }

    // Method to list all vehicles
    @Override
    public List<Vehicles> listVehicles() {
        return vehicleRepository.listVehicles();
    }

    // Method to list vehicles by status
    @Override
    public List<Vehicles> listVehiclesStatus(VehicleStatus status) {
        return vehicleRepository.listVehiclesStatus(status);
    }

    // Method to list vehicles by type
    @Override
    public List<Vehicles> listVehiclesType(VehicleType type) {
        return vehicleRepository.listVehiclesType(type);
    }

    // Method to list vehicles by ascending price
    @Override
    public List<Vehicles> listVehiclesPriceAsc() {
        return vehicleRepository.listVehiclesPriceAsc();
    }

    // Method to list vehicles by descending price
    @Override
    public List<Vehicles> listVehiclesPriceDesc() {
        return vehicleRepository.listVehiclesPriceDesc();
    }
}
