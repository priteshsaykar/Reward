package com.reward.demo.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerId;
    private double amount;
    private LocalDate date;

    public Transaction(double amount, String customerId, LocalDate date, Long id) {
        this.amount = amount;
        this.customerId = customerId;
        this.date = date;
        this.id = id;
    }

    public Transaction() {
    }

}
