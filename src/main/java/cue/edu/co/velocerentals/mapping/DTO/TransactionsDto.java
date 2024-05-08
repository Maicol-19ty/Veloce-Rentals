package cue.edu.co.velocerentals.mapping.DTO;

import cue.edu.co.velocerentals.enums.PaymentMethods;

import java.time.LocalDateTime;

public record TransactionsDto(Integer transaction_id, Integer reservation_id, double amount,
                              LocalDateTime transaction_date, PaymentMethods payment_method) {
}
