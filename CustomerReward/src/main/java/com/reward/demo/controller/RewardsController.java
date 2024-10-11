package com.reward.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reward.demo.exceptionhandler.MandatoryFieldException;
import com.reward.demo.service.RewardsService;

@RestController
@RequestMapping("/custRewards")
public class RewardsController {

    @Autowired
    private RewardsService rewardsService;

    @GetMapping("/monthly/{customerId}")
    public int getMonthlyPoints(@PathVariable String customerId, @RequestParam int month, @RequestParam int year) throws MandatoryFieldException {
        Optional.ofNullable(month)
                .filter(m -> m != 0)
                .orElseThrow(() -> new MandatoryFieldException("Month is mandatory"));

        Optional.ofNullable(year)
                .filter(y -> y != 0)
                .orElseThrow(() -> new MandatoryFieldException("Year is mandatory"));

        return rewardsService.getMonthlyPoints(customerId, month, year);
    }

    @GetMapping("/total/{customerId}")
    public int getTotalPoints(@PathVariable String customerId) {

        return rewardsService.getTotalPoints(customerId);

    }

}
