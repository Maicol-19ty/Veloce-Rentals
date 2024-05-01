package cue.edu.co.velocerentals.models;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder

public class Reservations {
   private int reservation_id;
   private String user_id;
   private String vehicle_id;
   private Date start_date;
   private Date end_date;
   private double total_cost;
}
