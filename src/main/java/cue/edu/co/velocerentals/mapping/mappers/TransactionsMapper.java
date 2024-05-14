package cue.edu.co.velocerentals.mapping.mappers;

import cue.edu.co.velocerentals.mapping.DTO.TransactionsDto;
import cue.edu.co.velocerentals.models.Transactions;

// Mapper class for converting between Transactions and TransactionsDto objects.
public class TransactionsMapper {

    // Method to map from Transactions model to TransactionsDto DTO.
    public static TransactionsDto mapFromModel(Transactions transactions) {
        return new TransactionsDto(transactions.getTransaction_id(), transactions.getReservation_id(), transactions.getAmount(),
                transactions.getTransaction_date(), transactions.getPayment_method());
    }

    // Method to map from TransactionsDto DTO to Transactions model.
    public static Transactions mapFromDTO(TransactionsDto transactionsDto) {
        return Transactions.builder()
                .transaction_id(transactionsDto.transaction_id())
                .reservation_id(transactionsDto.reservation_id())
                .amount(transactionsDto.amount())
                .transaction_date(transactionsDto.transaction_date())
                .payment_method(transactionsDto.payment_method())
                .build();
    }

}
