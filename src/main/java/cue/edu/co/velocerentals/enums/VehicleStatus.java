package cue.edu.co.velocerentals.enums;

public enum VehicleStatus {

    AVAILABLE("Available"),
    RENTED("Rented"),
    MAINTENANCE("Maintenance");

    private final String status;

    VehicleStatus(String type) {
        this.status = type;
    }

    public String getType() {
        return this.status;
    }

    public static VehicleStatus fromString(String type) {
        for (VehicleStatus v : VehicleStatus.values()) {
            if (v.status.equalsIgnoreCase(type)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with text " + type + " found");
    }
}
