package com.example.hotelsoptimizationapi;

import com.example.hotelsoptimizationapi.model.AllocationResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
public class RoomAllocationServiceTest {

    private RoomAllocationService service = new RoomAllocationService();

    @Test
    public void testScenarioOne() {
        Integer[] bids = {23, 45, 155, 374, 22, 99, 100, 101, 115, 209};
        AllocationResult result = service.allocateRooms(3, 3, Arrays.asList(bids));
        assertEquals(3, result.getUsedPremiumRooms(), "Scenario 1: Used Premium Rooms");
        assertEquals(3, result.getUsedEconomyRooms(), "Scenario 1: Used Economy Rooms");
        assertEquals(738, result.getTotalRevenuePremium(), "Scenario 1: Total Revenue Premium");
        assertEquals(167, result.getTotalRevenueEconomy(), "Scenario 1: Total Revenue Economy");
    }

    @Test
    public void testScenarioTwo() {
        Integer[] bids = {23, 45, 155, 374, 22, 99, 100, 101, 115, 209};
        AllocationResult result = service.allocateRooms(7, 5, Arrays.asList(bids));
        assertEquals(6, result.getUsedPremiumRooms(), "Scenario 2: Used Premium Rooms");
        assertEquals(4, result.getUsedEconomyRooms(), "Scenario 2: Used Economy Rooms");
        assertEquals(1054, result.getTotalRevenuePremium(), "Scenario 2: Total Revenue Premium");
        assertEquals(189, result.getTotalRevenueEconomy(), "Scenario 2: Total Revenue Economy");
    }

    @Test
    public void testScenarioThree() {
        Integer[] bids = {23, 45, 155, 374, 22, 99, 100, 101, 115, 209};
        AllocationResult result = service.allocateRooms(2, 7, Arrays.asList(bids));
        assertEquals(2, result.getUsedPremiumRooms(), "Scenario 3: Used Premium Rooms");
        assertEquals(4, result.getUsedEconomyRooms(), "Scenario 3: Used Economy Rooms");
        assertEquals(583, result.getTotalRevenuePremium(), "Scenario 3: Total Revenue Premium");
        assertEquals(189, result.getTotalRevenueEconomy(), "Scenario 3: Total Revenue Economy");
    }

    @Test
    public void testScenarioFour() {
        Integer[] bids = {23, 45, 155, 374, 22, 99, 100, 101, 115, 209};
        AllocationResult result = service.allocateRooms(10, 1, Arrays.asList(bids));
        assertEquals(7, result.getUsedPremiumRooms(), "Scenario 4: Used Premium Rooms");
        assertEquals(1, result.getUsedEconomyRooms(), "Scenario 4: Used Economy Rooms");
        assertEquals(1153, result.getTotalRevenuePremium(), "Scenario 4: Total Revenue Premium");
        assertEquals(45, result.getTotalRevenueEconomy(), "Scenario 4: Total Revenue Economy");
    }
}