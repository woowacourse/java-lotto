package domain;

import domain.numberscontainer.SixLottoNumbersDTO;
import domain.numberscontainer.Ticket;
import util.SixLottoNumbersFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoStore {
    public static List<Ticket> generateTickets(int ticketSize) {
        List<Ticket> tickets = new ArrayList<>();

        for (int i = 0; i < ticketSize; i++) {
            tickets.add(new Ticket(new SixLottoNumbersDTO(SixLottoNumbersFactory.createRandom())));
        }
        return tickets;
    }

    public static List<Ticket> generateTickets(int ticketSize, List<Set<Integer>> givenNumbers) {
        List<Ticket> tickets = givenNumbers.stream()
                .map(SixLottoNumbersFactory::createFixed)
                .map(SixLottoNumbersDTO::new)
                .map(Ticket::new)
                .collect(Collectors.toList());

        int randomTicketsSize = getRandomTicketsSize(ticketSize, givenNumbers);
        for (int i = 0; i < randomTicketsSize; i++) {
            tickets.add(new Ticket(new SixLottoNumbersDTO(SixLottoNumbersFactory.createRandom())));
        }
        return tickets;
    }

    private static int getRandomTicketsSize(int number, List<Set<Integer>> givenNumbers) {
        return number - givenNumbers.size();
    }
}
