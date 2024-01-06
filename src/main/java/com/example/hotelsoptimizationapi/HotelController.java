package com.example.hotelsoptimizationapi;

import com.example.hotelsoptimizationapi.model.AllocationRequest;
import com.example.hotelsoptimizationapi.model.AllocationResponse;
import com.example.hotelsoptimizationapi.model.AllocationResult;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@AllArgsConstructor
@RestController
@RequestMapping("/api/hotel")
public class HotelController {
    private RoomAllocationService roomAllocationService;

    @PostMapping("/allocateRooms")
    public AllocationResponse allocateRooms(@RequestBody AllocationRequest request) {

        AllocationResult result = roomAllocationService.allocateRooms(request.getFreePremiumRooms(), request.getFreeEconomyRooms(), request.getGuestBids());

        return new AllocationResponse(result.getUsedPremiumRooms(), result.getUsedEconomyRooms(), result.getTotalRevenuePremium(), result.getTotalRevenueEconomy());
    }
}