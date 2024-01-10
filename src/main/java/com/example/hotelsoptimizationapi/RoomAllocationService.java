package com.example.hotelsoptimizationapi;

import com.example.hotelsoptimizationapi.exception.InvalidBidException;
import com.example.hotelsoptimizationapi.model.AllocationCommand;
import com.example.hotelsoptimizationapi.model.AllocationMapper;
import com.example.hotelsoptimizationapi.model.AllocationRequest;
import com.example.hotelsoptimizationapi.model.AllocationResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@AllArgsConstructor
@Service
public class RoomAllocationService {

    private final AllocationMapper mapper;

    public AllocationResponse allocateRooms(AllocationCommand command) {
        AllocationRequest request = mapper.commandToRequest(command);

        if (command.getGuestBids().stream().anyMatch(bid -> bid < 0)) {
            throw new InvalidBidException("Guest bids cannot be negative");
        }

        int freePremiumRooms = request.getFreePremiumRooms();
        int freeEconomyRooms = request.getFreeEconomyRooms();
        List<Integer> guestBids = request.getGuestBids();

        Collections.sort(guestBids, Collections.reverseOrder());

        int usedPremiumRooms = 0, usedEconomyRooms = 0;
        int totalRevenuePremium = 0, totalRevenueEconomy = 0;
        List<Integer> economyBids = new ArrayList<>();
        int notLocated = 0;

        for (int bid : guestBids) {
            if (bid >= 100 && freePremiumRooms > 0) {
                usedPremiumRooms++;
                totalRevenuePremium += bid;
                freePremiumRooms--;
                System.out.println(bid + " allocated to Premium");
            } else if (bid < 100) {
                if (freeEconomyRooms > 0) {
                    usedEconomyRooms++;
                    totalRevenueEconomy += bid;
                    freeEconomyRooms--;
                    economyBids.add(bid);
                    System.out.println(bid + " allocated to Economy");
                } else {
                    notLocated++;
                }
            }
        }

        if (freeEconomyRooms < notLocated) {
            Collections.sort(economyBids, Collections.reverseOrder());
            for (int bid : economyBids) {
                if (freePremiumRooms > 0) {
                    usedPremiumRooms++;
                    totalRevenuePremium += bid;
                    totalRevenueEconomy -= bid;
                    freePremiumRooms--;
                    usedEconomyRooms--;
                    freeEconomyRooms++;
                    System.out.println(bid + " upgraded from Economy to Premium");
                }
            }

            for (int bid : guestBids) {
                if (bid < 100 && !economyBids.contains(bid) && freeEconomyRooms > 0) {
                    usedEconomyRooms++;
                    totalRevenueEconomy += bid;
                    freeEconomyRooms--;
                    System.out.println(bid + " allocated to Economy after upgrade attempt");
                }
            }
        }


        System.out.println("Final allocation result: Premium rooms=" + usedPremiumRooms + ", Economy rooms=" + usedEconomyRooms);
        System.out.println("Total Revenue: Premium - " + totalRevenuePremium + ", Economy - " + totalRevenueEconomy + ", Total - " + (totalRevenuePremium + totalRevenueEconomy));

        return new AllocationResponse(usedPremiumRooms, usedEconomyRooms, totalRevenuePremium, totalRevenueEconomy);
    }


}


