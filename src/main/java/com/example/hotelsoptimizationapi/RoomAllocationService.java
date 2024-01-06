package com.example.hotelsoptimizationapi;

import com.example.hotelsoptimizationapi.model.AllocationResult;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoomAllocationService {

    public AllocationResult allocateRooms(int freePremiumRooms, int freeEconomyRooms, List<Integer> guestBids) {
        Collections.sort(guestBids, Collections.reverseOrder());

        int usedPremiumRooms = 0, usedEconomyRooms = 0;
        int totalRevenuePremium = 0, totalRevenueEconomy = 0;
        List<Integer> potentialUpgrades = new ArrayList<>();

        for (int bid : guestBids) {
            if (bid >= 100 && freePremiumRooms > 0) {
                usedPremiumRooms++;
                totalRevenuePremium += bid;
                freePremiumRooms--;
                System.out.println(bid + " Premium");
            } else if (bid < 100) {
                // If there's no free economy room left, consider as a potential upgrade.
                if (freeEconomyRooms > 0) {
                    usedEconomyRooms++;
                    totalRevenueEconomy += bid;
                    freeEconomyRooms--;
                    System.out.println(bid + " Economy");
                } else {
                    potentialUpgrades.add(bid);
                    System.out.println(bid);
                }
            }
        }

        // Upgrade the highest bids from the economy to premium rooms if available
        for (int bid : potentialUpgrades) {
            if (freePremiumRooms > 0) {
                usedPremiumRooms++;
                totalRevenuePremium += bid;
                freePremiumRooms--;
                // No decrement in usedEconomyRooms as these guests were not allocated to economy yet.
            }
        }

        System.out.println("Final allocation result: Premium rooms=" + usedPremiumRooms + ", Economy rooms=" + usedEconomyRooms);
        return new AllocationResult(usedPremiumRooms, usedEconomyRooms, totalRevenuePremium, totalRevenueEconomy);
    }



//        // Upgrade the highest economy bid to premium if there is a vacancy
//        if (!economyGuests.isEmpty() && freePremiumRooms > 0) {
//            // Find the highest economy bid for upgrading
//            int highestEconomyBid = Collections.max(economyGuests);
//            economyGuests.remove(Integer.valueOf(highestEconomyBid)); // Remove this bid from the economy list
//            System.out.println(highestEconomyBid);
//            usedPremiumRooms++;
//            totalRevenuePremium += highestEconomyBid;
//            freePremiumRooms--;
//            System.out.println("Upgraded to premium room: Economy bid " + highestEconomyBid);
//
//            // Attempt to allocate the now vacated economy room to the next highest economy bid
//            if (freeEconomyRooms > 0 && !economyGuests.isEmpty()) {
//                int nextHighestEconomyBid = Collections.max(economyGuests);
//                economyGuests.remove(Integer.valueOf(nextHighestEconomyBid));
//                System.out.println(nextHighestEconomyBid);
//                usedEconomyRooms++;
//                totalRevenueEconomy += nextHighestEconomyBid;
//                freeEconomyRooms--;
//                System.out.println("Reallocated to economy room: Bid " + nextHighestEconomyBid);
//            }
//        }








}
