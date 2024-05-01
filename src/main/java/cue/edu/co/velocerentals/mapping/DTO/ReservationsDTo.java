package cue.edu.co.velocerentals.mapping.DTO;

import java.util.Date;

public record ReservationsDTo(Integer reservation_id, String user_id, String vehicle_id,
                              Date start_date, Date end_date, double total_cost) {
}
