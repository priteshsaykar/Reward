package com.reward.demo.service;



import com.reward.demo.model.Transaction;
import com.reward.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RewardsService {

    @Autowired
    private TransactionRepository transactionRepository;

    public int calculatePoints(double amount) {
        int points = 0;
        if (amount > 100) {
            points += (amount - 100) * 2;
            amount = 100;
        }
        if (amount > 50) {
            points += (amount - 50);
        }
        return points;
    }

    public int getMonthlyPoints(String customerId, int month, int year) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
        List<Transaction> transactions = transactionRepository.findByCustomerIdAndDateBetween(customerId, startDate, endDate);
        return transactions.stream().mapToInt(t -> calculatePoints(t.getAmount())).sum();
    }

    public int getTotalPoints(String customerId) {
        List<Transaction> transactions = transactionRepository.findByCustomerIdAndDateBetween(customerId, LocalDate.now().minusMonths(3), LocalDate.now());
        return transactions.stream().mapToInt(t -> calculatePoints(t.getAmount())).sum();
    }
}

