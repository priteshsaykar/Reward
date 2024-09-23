package com.reward.demo.controller;


import com.reward.demo.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rewards")
public class RewardsController {

    @Autowired
    private RewardsService rewardsService;

    @GetMapping("/monthly/{customerId}")
    public int getMonthlyPoints(@PathVariable String customerId, @RequestParam int month, @RequestParam int year) {
        return rewardsService.getMonthlyPoints(customerId, month, year);
    }

    @GetMapping("/total/{customerId}")
    public int getTotalPoints(@PathVariable String customerId) {
        return rewardsService.getTotalPoints(customerId);
    }
}
