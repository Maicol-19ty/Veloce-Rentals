package cue.edu.co.velocerentals.models;

import jakarta.enterprise.context.SessionScoped;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
@SessionScoped

public class Reservations implements Serializable {
   private int reservation_id;
   private String user_id;
   private String vehicle_id;
   private Date start_date;
   private Date end_date;
   private double total_cost;
}
