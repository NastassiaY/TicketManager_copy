package com.danliuk.service;

import com.danliuk.model.BusTicket;

import java.time.LocalDate;


public class Validator {
    public static int checkedTicketCount = 0;
    public static int validTicketCount = 0;

    private static int startDateErrorCount = 0;
    private static int ticketTypeErrorCount = 0;
    private static int priceErrorCount = 0;

    public static boolean isTicketValid(BusTicket ticket) {
        boolean isValid;
        if(isTicketTypeValid(ticket) & isStartDateValid(ticket) & isPriceValid(ticket)) {
            validTicketCount++;
            isValid = true;
        } else {
            isValid = false;
        }

        checkedTicketCount++;
        return isValid;
    }

    private static boolean isTicketTypeValid(BusTicket ticket) {
        String ticketType = ticket.getTicketType();
        if(ticketType == null || ticketType.isEmpty() || ticketType.equals("PRIME")) {
            ticketTypeErrorCount++;
            return false;
        } else {
            return true;
        }
    }
    private static boolean isStartDateValid(BusTicket ticket) {
        String startDate = ticket.getStartDate();
        if(startDate == null || startDate.isEmpty() || LocalDate.parse(startDate).isAfter(LocalDate.now())) {
            startDateErrorCount++;
            return false;
        } else {
            return true;
        }
    }
    private static boolean isPriceValid(BusTicket ticket) {
        String price = ticket.getPrice();
        if(price == null || price.isEmpty() || price.equals("0") || (Integer.parseInt(price) % 2) != 0) {
            priceErrorCount++;
            return false;
        } else {
            return true;
        }
    }

    public static String getMostPopularViolation() {
        String popularViolation;
        if(ticketTypeErrorCount > startDateErrorCount) {
            if (ticketTypeErrorCount > priceErrorCount) {
                popularViolation = "ticket type";
            } else {
                popularViolation = "price";
            }
        } else if(startDateErrorCount > priceErrorCount) {
            popularViolation = "start date";
        } else {
            popularViolation = "price";
        }
        return popularViolation;
    }
}
