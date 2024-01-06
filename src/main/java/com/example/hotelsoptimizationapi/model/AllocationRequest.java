package com.example.hotelsoptimizationapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllocationRequest {
    private int freePremiumRooms;
    private int freeEconomyRooms;
    private List<Integer> guestBids;

    @Override
    public String toString() {
        return "AllocationRequest{" +
                "freePremiumRooms=" + freePremiumRooms +
                ", freeEconomyRooms=" + freeEconomyRooms +
                ", guestBids=" + guestBids +
                '}';
    }
}
