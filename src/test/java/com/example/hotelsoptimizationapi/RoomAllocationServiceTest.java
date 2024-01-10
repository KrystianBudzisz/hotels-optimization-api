package com.example.hotelsoptimizationapi;

import com.example.hotelsoptimizationapi.exception.InvalidBidException;
import com.example.hotelsoptimizationapi.model.AllocationCommand;
import com.example.hotelsoptimizationapi.model.AllocationMapper;
import com.example.hotelsoptimizationapi.model.AllocationRequest;
import com.example.hotelsoptimizationapi.model.AllocationResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class RoomAllocationServiceTest {


    @Mock
    private AllocationMapper mapper;

    @InjectMocks
    private RoomAllocationService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        when(mapper.commandToRequest(any(AllocationCommand.class)))
                .thenAnswer(invocation -> {
                    AllocationCommand command = invocation.getArgument(0);
                    return new AllocationRequest(
                            command.getFreePremiumRooms(),
                            command.getFreeEconomyRooms(),
                            command.getGuestBids()
                    );
                });
    }

    @Test
    public void testScenarioOne() {
        AllocationCommand command = new AllocationCommand(3, 3, Arrays.asList(23, 45, 155, 374, 22, 99, 100, 101, 115, 209));
        AllocationResponse response = service.allocateRooms(command);
        assertEquals(3, response.getUsedPremiumRooms(), "Scenario 1: Used Premium Rooms");
        assertEquals(3, response.getUsedEconomyRooms(), "Scenario 1: Used Economy Rooms");
        assertEquals(738, response.getTotalRevenuePremium(), "Scenario 1: Total Revenue Premium");
        assertEquals(167, response.getTotalRevenueEconomy(), "Scenario 1: Total Revenue Economy");
    }

    @Test
    public void testScenarioTwo() {
        AllocationCommand command = new AllocationCommand(7, 5, Arrays.asList(23, 45, 155, 374, 22, 99, 100, 101, 115, 209));
        AllocationResponse response = service.allocateRooms(command);
        assertEquals(6, response.getUsedPremiumRooms(), "Scenario 2: Used Premium Rooms");
        assertEquals(4, response.getUsedEconomyRooms(), "Scenario 2: Used Economy Rooms");
        assertEquals(1054, response.getTotalRevenuePremium(), "Scenario 2: Total Revenue Premium");
        assertEquals(189, response.getTotalRevenueEconomy(), "Scenario 2: Total Revenue Economy");
    }

    @Test
    public void testScenarioThree() {
        AllocationCommand command = new AllocationCommand(2, 7, Arrays.asList(23, 45, 155, 374, 22, 99, 100, 101, 115, 209));
        AllocationResponse response = service.allocateRooms(command);
        assertEquals(2, response.getUsedPremiumRooms(), "Scenario 3: Used Premium Rooms");
        assertEquals(4, response.getUsedEconomyRooms(), "Scenario 3: Used Economy Rooms");
        assertEquals(583, response.getTotalRevenuePremium(), "Scenario 3: Total Revenue Premium");
        assertEquals(189, response.getTotalRevenueEconomy(), "Scenario 3: Total Revenue Economy");
    }

    @Test
    public void testScenarioFour() {
        AllocationCommand command = new AllocationCommand(10, 1, Arrays.asList(23, 45, 155, 374, 22, 99, 100, 101, 115, 209));
        AllocationResponse response = service.allocateRooms(command);
        assertEquals(7, response.getUsedPremiumRooms(), "Scenario 4: Used Premium Rooms");
        assertEquals(1, response.getUsedEconomyRooms(), "Scenario 4: Used Economy Rooms");
        assertEquals(1153, response.getTotalRevenuePremium(), "Scenario 4: Total Revenue Premium");
        assertEquals(45, response.getTotalRevenueEconomy(), "Scenario 4: Total Revenue Economy");
    }

    @Test
    public void whenValidInput_thenCorrectlyAllocateRooms() {
        AllocationCommand command = new AllocationCommand(3, 3, Arrays.asList(23, 45, 155, 374, 22, 99, 100, 101, 115, 209));

        AllocationResponse response = service.allocateRooms(command);

        assertEquals(3, response.getUsedPremiumRooms());
        assertEquals(3, response.getUsedEconomyRooms());
    }


    @Test
    public void whenNoRoomsAvailable_thenNoRoomsAllocated() {
        AllocationCommand command = new AllocationCommand(0, 0, Arrays.asList(100, 150, 200));

        AllocationResponse response = service.allocateRooms(command);

        assertEquals(0, response.getUsedPremiumRooms());
        assertEquals(0, response.getUsedEconomyRooms());

    }

    @Test
    public void whenGuestBidsAreNull_thenThrowConstraintViolationException() {
        AllocationCommand command = new AllocationCommand(3, 3, null);

        assertThrows(NullPointerException.class, () -> {
            service.allocateRooms(command);
        });
    }

    @Test
    public void whenGuestBidsContainNegative_thenExpectIllegalArgumentException() {
        AllocationCommand command = new AllocationCommand(3, 3, Arrays.asList(-100, 200, 300));

        Exception exception = assertThrows(InvalidBidException.class, () -> {
            service.allocateRooms(command);
        });

        String expectedMessage = "Guest bids cannot be negative";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


}