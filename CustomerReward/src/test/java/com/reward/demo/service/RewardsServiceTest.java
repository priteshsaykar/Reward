package com.reward.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.reward.demo.model.Transaction;
import com.reward.demo.repository.TransactionRepository;

@ExtendWith(MockitoExtension.class)
public class RewardsServiceTest {

    @InjectMocks
    private RewardsService service;

    @Mock
    private TransactionRepository repository;

    @Test
    public void testCalculatePointsWithAmountLessThan50() {
        double amount = 30.0;
        int expectedPoints = 0;

        double actualPoints = service.calculatePoints(amount);

        assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void testCalculatePointsWithAmountEqualTo50() {
        double amount = 50.0;
        int expectedPoints = 0;

        double actualPoints = service.calculatePoints(amount);

        assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void testCalculatePointsWithAmountGreaterThan50AndLessThan100() {
        double amount = 75.0;
        int expectedPoints = 25;

        double actualPoints = service.calculatePoints(amount);

        assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void testCalculatePointsWithAmountEqualTo100() {
        double amount = 100.0;
        double expectedPoints = 50;

        double actualPoints = service.calculatePoints(amount);

        assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void testCalculatePointsWithAmountGreaterThan100() {
        double amount = 150.0;
        double expectedPoints = 150;

        double actualPoints = service.calculatePoints(amount);

        assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void testGetMonthlyPointsWithNoTransactions() {
        String customerId = "123";
        int month = 9;
        int year = 2022;
        List<Transaction> transactions = new ArrayList<>();

        when(repository.findByCustomerIdAndDateBetween(customerId, LocalDate.of(year, month, 1), LocalDate.of(year, month, 30)))
                .thenReturn(transactions);

        double expectedPoints = 0.0;
        double actualPoints = service.getMonthlyPoints(customerId, month, year);

        assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void testGetMonthlyPointsWithTransactions() {
        String customerId = "123";
        int month = 9;
        int year = 2022;
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(50.0, customerId, LocalDate.of(year, month, 5), 13l));
        transactions.add(new Transaction(100.0, customerId, LocalDate.of(year, month, 15), 14l));
        transactions.add(new Transaction(75.0, customerId, LocalDate.of(year, month, 25), 15l));

        when(repository.findByCustomerIdAndDateBetween(any(String.class), any(LocalDate.class), any(LocalDate.class))).thenReturn(transactions);
        double expectedPoints = 75.0;
        double actualPoints = service.getMonthlyPoints(customerId, month, year);

        assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void testGetTotalPointsWithNoTransactions() {
        String customerId = "123";
        List<Transaction> transactions = new ArrayList<>();

        when(repository.findByCustomerIdAndDateBetween(customerId, LocalDate.now().minusMonths(3), LocalDate.now()))
                .thenReturn(transactions);

        double expectedPoints = 0.0;
        double actualPoints = service.getTotalPoints(customerId);

        assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void testGetTotalPointsWithTransactions() {
        String customerId = "123";
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(50.0, customerId, LocalDate.now().minusDays(2), 13l));
        transactions.add(new Transaction(100.0, customerId, LocalDate.now().minusDays(1), 14l));
        transactions.add(new Transaction(75.0, customerId, LocalDate.now(), 15l));

        when(repository.findByCustomerIdAndDateBetween(anyString(), any(LocalDate.class
        ), any(LocalDate.class
        )))
                .thenReturn(transactions);

        double expectedPoints = 75.0;
        double actualPoints = service.getTotalPoints(customerId);

        assertEquals(expectedPoints, actualPoints);
    }
}
