package domain;

import domain.numberscontainer.Ticket;
import util.LottoNumbersDtoGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoStore {
    public static List<Ticket> generateTickets(long number) {
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            tickets.add(new Ticket(LottoNumbersDtoGenerator.generateRandomTicketDto()));
        }
        return tickets;
    }

    public static List<Ticket> generateTickets(long number, List<Set<Integer>> myNumbers) {
        List<Ticket> tickets = myNumbers.stream()
                .map(numbers -> LottoNumbersDtoGenerator.generateFixedNumberDto(numbers, -1))
                .map(dto -> new Ticket(dto))
                .collect(Collectors.toList());

        long randomTicketsNumber = getRandomTicketsNumber(number, myNumbers);
        for (int i = 0; i < randomTicketsNumber; i++) {
            tickets.add(new Ticket(LottoNumbersDtoGenerator.generateRandomTicketDto()));
        }
        return tickets;
    }

    private static long getRandomTicketsNumber(long number, List<Set<Integer>> myNumbers) {
        return number - myNumbers.size();
    }
}
