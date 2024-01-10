package com.example.hotelsoptimizationapi;

import com.example.hotelsoptimizationapi.model.AllocationCommand;
import com.example.hotelsoptimizationapi.model.AllocationResponse;
import jakarta.validation.Valid;
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
    public AllocationResponse allocateRooms(@Valid @RequestBody AllocationCommand command) {
        return roomAllocationService.allocateRooms(command);
    }
}