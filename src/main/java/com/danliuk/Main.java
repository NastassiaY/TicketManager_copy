package com.danliuk;

import com.danliuk.model.BusTicket;
import com.danliuk.service.Validator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import static com.danliuk.service.Validator.checkedTicketCount;
import static com.danliuk.service.Validator.validTicketCount;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {

        int x = 0;

        do {
            String input = getInput();
            BusTicket busTicket = new ObjectMapper().readValue(input, BusTicket.class);

            if(Validator.isTicketValid(busTicket)) {
                System.out.println("The ticket is valid");
            } else {
                System.out.println("The ticket is NOT valid");
            }

            System.out.println(busTicket.toString());
            x++;

        } while (x < 5);


        List<String> busTickets = getAllBusTicketsFromFile("src/main/resources/ticketData.txt");
        assert busTickets != null;
        for (String ticket : busTickets) {
            BusTicket busTicket = new ObjectMapper().readValue(ticket, BusTicket.class);
            if(Validator.isTicketValid(busTicket)) {
                System.out.println("The ticket is valid");
            } else {
                System.out.println("The ticket is NOT valid");
            }

            System.out.println(busTicket.toString());

        }

        System.out.printf("Total: %d; Valid: %d; Most popular violation: %s",
                checkedTicketCount, validTicketCount, Validator.getMostPopularViolation());
    }

    private static String getInput() {
        return new Scanner(System.in).nextLine();
    }

    private static List<String> getAllBusTicketsFromFile(String path) {
        List<String> busTickets = null;
        try {
            busTickets = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return busTickets;
    }
}
