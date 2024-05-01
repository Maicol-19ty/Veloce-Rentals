package cue.edu.co.velocerentals.mapping.mappers;


import cue.edu.co.velocerentals.mapping.DTO.ReservationsDTo;
import cue.edu.co.velocerentals.models.Reservations;

public class ReservationsMapper {

    public static ReservationsDTo mapFromModel(Reservations reservations) {
        return new ReservationsDTo(reservations.getReservation_id(), reservations.getUser_id(), reservations.getVehicle_id(),
                reservations.getStart_date(), reservations.getEnd_date(), reservations.getTotal_cost());
    }

    public static Reservations mapFromDTO(ReservationsDTo reservationsDTo) {
        return Reservations.builder()
                .reservation_id(reservationsDTo.reservation_id())
                .user_id(reservationsDTo.user_id())
                .vehicle_id(reservationsDTo.vehicle_id())
                .start_date(reservationsDTo.start_date())
                .end_date(reservationsDTo.end_date())
                .total_cost(reservationsDTo.total_cost())
                .build();
    }

}
