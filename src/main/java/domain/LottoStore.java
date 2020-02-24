package domain;

import domain.numberscontainer.SixLottoNumbersDTO;
import domain.numberscontainer.Ticket;
import strategy.RandomTicketFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Ticket 리스트 생성
 */
public class LottoStore {

    public static List<Ticket> generateTickets(int ticketSize) {
        List<Ticket> tickets = new ArrayList<>();

        for (int i = 0; i < ticketSize; i++) {
            tickets.add(RandomTicketFactory.createTicket());
        }
        return tickets;
    }

    public static List<Ticket> generateTickets(int ticketSize, List<SixLottoNumbersDTO> givenNumbers) {
        List<Ticket> tickets = givenNumbers.stream()
                .map(Ticket::new)
                .collect(Collectors.toList());

        int randomTicketsSize = getRandomTicketSize(ticketSize, givenNumbers.size());
        tickets.addAll(generateTickets(randomTicketsSize));

        return tickets;
    }

    private static int getRandomTicketSize(int totalTicketSize, int manualTicketSize) {
        return totalTicketSize - manualTicketSize;
    }
}