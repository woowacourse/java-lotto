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

    public static final int MIN_TICKET_SIZE = 1;

    public static List<Ticket> createTickets(int randomTicketSize) {
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < randomTicketSize; i++) {
            tickets.add(RandomTicketFactory.createTicket());
        }
        return tickets;
    }

    public static List<Ticket> createTickets(int totalTicketSize, int manualTicketSize, List<LottoNumbersDto> givenNumbers) {
        validateManualTicketSize(totalTicketSize, manualTicketSize);
        List<Ticket> tickets = givenNumbers.stream()
                .map(Ticket::new)
                .collect(Collectors.toList());

        int randomTicketsSize = getRandomTicketSize(totalTicketSize, givenNumbers.size());
        tickets.addAll(createTickets(randomTicketsSize));

        return tickets;
    }

    private static void validateManualTicketSize(int totalTicketSize, int manualTicketSize) {
        if (manualTicketSize < MIN_TICKET_SIZE) {
            throw new IllegalArgumentException("1장 이상 구매 가능합니다.");
        }
        if (manualTicketSize > totalTicketSize) {
            throw new IllegalArgumentException(String.format("최대 %d장 구매 가능합니다.", totalTicketSize));
        }
    }

    public static int getRandomTicketSize(int totalTicketSize, int manualTicketSize) {
        return totalTicketSize - manualTicketSize;
    }
}