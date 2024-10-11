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
            points += (int) ((amount - 100) * 2);
            amount = 100;
        }
        if (amount > 50) {
            points += (int) (amount - 50);
        }
        return points;
    }

    public int getMonthlyPoints(String customerId, int month, int year) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
        List<Transaction> transactions = transactionRepository.findByCustomerIdAndDateBetween(customerId, startDate, endDate);
        int totalPoints = 0;
        for (Transaction transaction : transactions) {
            totalPoints += calculatePoints(transaction.getAmount());
        }
        return totalPoints;
    }

    public int getTotalPoints(String customerId) {
        LocalDate startDate = LocalDate.now().minusMonths(3);
        LocalDate endDate = LocalDate.now();
        List<Transaction> transactions = transactionRepository.findByCustomerIdAndDateBetween(customerId, startDate, endDate);
        int totalPoints = 0;
        for (Transaction transaction : transactions) {
            totalPoints += calculatePoints(transaction.getAmount());
        }
        return totalPoints;
    }
}
