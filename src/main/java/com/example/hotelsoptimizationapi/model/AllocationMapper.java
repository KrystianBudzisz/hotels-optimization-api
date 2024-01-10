package com.example.hotelsoptimizationapi.model;

import org.springframework.stereotype.Component;

@Component
public class AllocationMapper {
    public AllocationRequest commandToRequest(AllocationCommand command) {
        return new AllocationRequest(
                command.getFreePremiumRooms(),
                command.getFreeEconomyRooms(),
                command.getGuestBids()
        );
    }
}
