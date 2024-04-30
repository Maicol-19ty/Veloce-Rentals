package cue.edu.co.velocerentals.models;

import cue.edu.co.velocerentals.utils.PaymentMethods;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder

public class Transactions {
    private int transaction_id;
    private int reservation_id;
    private double amount;
    private LocalDateTime transaction_date;
    private PaymentMethods payment_method;
}
