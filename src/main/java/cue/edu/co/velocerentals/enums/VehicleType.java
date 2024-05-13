package cue.edu.co.velocerentals.enums;

import lombok.Getter;

@Getter
public enum VehicleType {

    CAR("Car"),
    MOTORCYCLE("Motorcycle");

    private final String type;

    VehicleType(String type) {
        this.type = type;
    }

    public static VehicleType fromString(String type) {
        for (VehicleType v : VehicleType.values()) {
            if (v.type.equalsIgnoreCase(type)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with text " + type + " found");
    }
}
