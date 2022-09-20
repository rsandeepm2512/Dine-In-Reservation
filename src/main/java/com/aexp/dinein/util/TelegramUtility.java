package com.aexp.dinein.util;

import com.aexp.dinein.model.Message;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TelegramUtility {

    public static final Map<Long, List<String>> optionsHistory = new HashMap<>();
    public static final Map<Long, List<String>> optionsValueHistory = new HashMap<>();
    public static final Map<Long, String> users = new HashMap<>();
    public static final Map<Long, String> chatIdMap = new HashMap<>();
    public static final Map<Long, List<String>> userToRestaurantsMap = new HashMap<>();
    public static final Map<String, List<String>> restaurantToTimeSlots = new HashMap<>();
    public static final List<String> restaurantList = new ArrayList<>();

    public static final Map<Long, List<String>> bookingMap = new HashMap<>();

    static {
        users.put(12345L, "Sandeep Kumar");

        optionsHistory.put(12345L, new ArrayList<>());

        optionsValueHistory.put(12345L, new ArrayList<>());

        chatIdMap.put(12345L, "1152726479");

        bookingMap.put(12345L, new ArrayList<>());

        restaurantList.addAll(List.of("Rajinder Da Dhaba", "Bukhara", "Olive Bar And Kitchen", "Bo Tai"));

        userToRestaurantsMap.put(12345L, restaurantList);

        restaurantToTimeSlots.put("Rajinder Da Dhaba", List.of("18:00", "19:00", "21:00"));
        restaurantToTimeSlots.put("Bukhara", List.of("17:00", "18:00", "21:00", "22:00"));
        restaurantToTimeSlots.put("Olive Bar And Kitchen", List.of("18:00", "19:00", "20:00"));
        restaurantToTimeSlots.put("Bo Tai", List.of("18:30", "19:00", "19:30", "20:00", "20:30"));
    }

    public static void resetData(Long userId) {
        optionsHistory.get(userId).clear();
        optionsValueHistory.get(userId).clear();
    }

    public static void initializeUser(Message result) {
        Long userId = result.getFrom().getId();

        users.put(userId, result.getFrom().getFirstName() + " " + result.getFrom().getLastName());
        optionsHistory.put(userId, new ArrayList<>());
        optionsValueHistory.put(userId, new ArrayList<>());
        bookingMap.put(userId, new ArrayList<>());
        userToRestaurantsMap.put(userId, restaurantList);
    }
}
