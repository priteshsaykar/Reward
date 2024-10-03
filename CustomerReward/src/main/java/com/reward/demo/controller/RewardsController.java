package com.reward.demo.controller;

import com.reward.demo.exceptionhandler.MandatoryFieldException;
import com.reward.demo.service.RewardsService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/custRewards")
public class RewardsController {

    @Autowired
    private RewardsService rewardsService;

    @GetMapping("/monthly/{customerId}")
    public double getMonthlyPoints(@PathVariable String customerId, @RequestParam int month, @RequestParam int year) throws MandatoryFieldException {
        Optional.ofNullable(month)
            .filter(m -> m != 0)
            .orElseThrow(() -> new MandatoryFieldException("Month is mandatory"));

        Optional.ofNullable(year)
            .filter(y -> y != 0)
            .orElseThrow(() -> new MandatoryFieldException("Year is mandatory"));

        return rewardsService.getMonthlyPoints(customerId, month, year);
    }
    @GetMapping("/total/{customerId}")
    public double getTotalPoints(@PathVariable String customerId)  {

        return rewardsService.getTotalPoints(customerId);

    }

}
