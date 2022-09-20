package com.aexp.dinein.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static com.aexp.dinein.util.TelegramUtility.*;

@Service
public class MessageProcessor {

    public String processMessage(Long userId, String userMessage) {
        if("stop".equalsIgnoreCase(userMessage)) {
            resetData(userId);
            return "Thank you for using our bot services!";
        }
        if("/start".equalsIgnoreCase(userMessage) || (users.containsKey(userId) && optionsHistory.get(userId).isEmpty())) {
            // Existing user started new session
            String userName = users.get(userId);
            List<String> restaurents = userToRestaurantsMap.get(userId);

            if(restaurents.isEmpty()) {
                optionsHistory.get(userId).add("1");
                optionsValueHistory.get(userId).add("new");
                return "Welcome to the American Express Dine-In Reservations.\nYour available options:\n1. New\nEnter 1 to start your reservation.";
            }

            StringBuilder message = new StringBuilder();
            message.append("Hi " + userName + ", Based on your history, from your frequent visited restaurants, following restaurants are accepting bookings. In case you want to try new restaurant, select New\n");
            int point = 1;
            for(String s : restaurents) {
                optionsHistory.get(userId).add(String.valueOf(point));
                optionsValueHistory.get(userId).add(s);
                message.append(point++ + ". " + s + "\n");
            }
            optionsHistory.get(userId).add(String.valueOf(point));
            optionsValueHistory.get(userId).add("new");
            message.append(point + ". New");
            return message.toString();
        } else if(users.containsKey(userId)) {
            // Existing user already in session
            List<String> options = optionsHistory.get(userId);
            if(options.contains(userMessage) || optionsValueHistory.get(userId).contains(userMessage.toLowerCase())) {
                // Valid input by user
                if("New".equalsIgnoreCase(userMessage)) {
                    return "New User!"; // TODO
                } else {
                    String value = optionsValueHistory.get(userId).get(options.indexOf(userMessage));
                    if(restaurantList.contains(value)) {
                        String restaurantName = value;
                        StringBuilder message = new StringBuilder();
                        message.append("Select your time slot from below available slots.\n");
                        int point = 1;
                        resetData(userId);
                        for (String timeSlot : restaurantToTimeSlots.get(restaurantName)) {
                            optionsHistory.get(userId).add(String.valueOf(point));
                            optionsValueHistory.get(userId).add(timeSlot);
                            message.append(point++ + ". " + timeSlot + "\n");
                        }
                        bookingMap.get(userId).add(restaurantName);
                        return message.toString();
                    } else {
                        String timeSlot = value;
                        String reservationId = UUID.randomUUID().toString().substring(0, 10);
                        bookingMap.get(userId).add(timeSlot);
                        bookingMap.get(userId).add(reservationId);
                        StringBuilder message = new StringBuilder();
                        message.append("Your reservation is confirmed with below details:\n");
                        message.append("Restaurant Name: ").append(bookingMap.get(userId).get(0)).append("\n");
                        message.append("Time Slot: ").append(timeSlot).append("\n");
                        message.append("Reservaion Id: ").append(reservationId);

                        resetData(userId);

                        return message.toString();
                    }
                }
            } else {
                return "Invalid selection, please retry from above options";
            }
        }
        // New user
        return "We are not supporting new users right now.\nPlease visit later!";
    }
}
