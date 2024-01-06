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

    private int usedPremiumRooms; // Number of premium rooms used
    private int usedEconomyRooms; // Number of economy rooms used
    private int totalRevenuePremium; // Total revenue from premium rooms
    private int totalRevenueEconomy; // Total revenue from economy rooms

    @Override
    public String toString() {
        return "AllocationResponse{" +
                "usedPremiumRooms=" + usedPremiumRooms +
                ", usedEconomyRooms=" + usedEconomyRooms +
                ", totalRevenuePremium=" + totalRevenuePremium +
                ", totalRevenueEconomy=" + totalRevenueEconomy +
                '}';
    }

}
