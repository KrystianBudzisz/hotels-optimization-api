package com.example.hotelsoptimizationapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllocationResponse {

    private int usedPremiumRooms;
    private int usedEconomyRooms;
    private int totalRevenuePremium;
    private int totalRevenueEconomy;


}
