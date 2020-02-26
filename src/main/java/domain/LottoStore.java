package domain;

import domain.numberscontainer.LottoNumbersDto;
import domain.numberscontainer.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Ticket 리스트 생성
 */
public class LottoStore {

    public static List<Ticket> createTickets(int ticketSize) {
        List<Ticket> tickets = new ArrayList<>();

        for (int i = 0; i < ticketSize; i++) {
            tickets.add(RandomTicketFactory.createTicket());
        }
        return tickets;
    }

    public static List<Ticket> createTickets(int totalTicketSize, List<LottoNumbersDto> givenNumbers) {
        List<Ticket> tickets = givenNumbers.stream()
                .map(Ticket::new)
                .collect(Collectors.toList());

        int randomTicketsSize = getRandomTicketSize(totalTicketSize, givenNumbers.size());
        tickets.addAll(createTickets(randomTicketsSize));

        return tickets;
    }

    public static int getRandomTicketSize(int totalTicketSize, int manualTicketSize) {
        return totalTicketSize - manualTicketSize;
    }
}