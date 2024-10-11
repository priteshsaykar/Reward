package com.reward.demo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.reward.demo.service.RewardsService;

@WebMvcTest(RewardsController.class)
public class RewardsControllerTest {

    @MockBean
    private RewardsService rewardsService;

    @InjectMocks
    private RewardsController rewardsController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(rewardsController).build();
    }

    @Test
    public void testGetMonthlyPoints() throws Exception {
        String customerId = "123";
        int month = 1;
        int year = 2022;
        int expectedPoints = 100;

        Mockito.when(rewardsService.getMonthlyPoints(customerId, month, year)).thenReturn(expectedPoints);

        mockMvc.perform(MockMvcRequestBuilders.get("/custRewards/monthly/{customerId}", customerId)
                .param("month", String.valueOf(month))
                .param("year", String.valueOf(year)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf(expectedPoints)));
    }

    @Test
    public void testGetTotalPoints() throws Exception {
        String customerId = "123";
        int expectedPoints = 500;

        Mockito.when(rewardsService.getTotalPoints(customerId)).thenReturn(expectedPoints);

        mockMvc.perform(MockMvcRequestBuilders.get("/custRewards/total/{customerId}", customerId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf(expectedPoints)));
    }
}
