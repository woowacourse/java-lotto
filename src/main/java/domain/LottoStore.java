package domain;

import domain.numberscontainer.LottoNumbersDtoAssembler;
import domain.numberscontainer.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Ticket 리스트 생성
 */
public class LottoStore {
    private static final int MIN_TICKET_SIZE = 1;
    private static final int MONEY_UNIT = 1000;

    private static int totalTicketSize;

    private static List<Ticket> createRandomTickets(int randomTicketSize) {
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < randomTicketSize; i++) {
            tickets.add(RandomTicketFactory.createTicket());
        }
        return tickets;
    }

    public static List<Ticket> createTickets(Money money, int manualTicketSize, List<String> givenNumbers) {
        totalTicketSize = getTotalTicketSize(money);
        validateManualTicketSize(manualTicketSize);
        List<Ticket> tickets = parseTicket(givenNumbers);
        int randomTicketSize = totalTicketSize - manualTicketSize;

        tickets.addAll(createRandomTickets(randomTicketSize));
        return tickets;
    }

    private static List<Ticket> parseTicket(List<String> givenNumbers) {
        return givenNumbers.stream()
                .map(LottoNumbersDtoAssembler::assemble)
                .map(Ticket::new)
                .collect(Collectors.toList());
    }

    private static void validateManualTicketSize(int manualTicketSize) {
        if (manualTicketSize < MIN_TICKET_SIZE) {
            throw new IllegalArgumentException(String.format("%d장 이상 구매 가능합니다.", MIN_TICKET_SIZE));
        }
        if (manualTicketSize > totalTicketSize) {
            throw new IllegalArgumentException(String.format("최대 %d장 구매 가능합니다.", totalTicketSize));
        }
    }

    private static int getTotalTicketSize(Money money) {
        return money.getValue() / MONEY_UNIT;
    }
}