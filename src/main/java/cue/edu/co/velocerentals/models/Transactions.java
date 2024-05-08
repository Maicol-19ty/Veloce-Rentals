package cue.edu.co.velocerentals.models;

import cue.edu.co.velocerentals.enums.PaymentMethods;
import jakarta.enterprise.context.SessionScoped;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
@SessionScoped

public class Transactions implements Serializable {
    private int transaction_id;
    private int reservation_id;
    private double amount;
    private LocalDateTime transaction_date;
    private PaymentMethods payment_method;
}
