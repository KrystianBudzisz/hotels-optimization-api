package com.example.hotelsoptimizationapi.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AllocationCommand {
    @Min(value = 0, message = "Number of premium rooms cannot be negative")
    private int freePremiumRooms;

    @Min(value = 0, message = "Number of economy rooms cannot be negative")
    private int freeEconomyRooms;

    @NotNull(message = "Guest bids cannot be null")
    @Min(value = 0, message = "Guest bids cannot be negative")
    private List<Integer> guestBids;

}
