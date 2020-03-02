package domain.numberscontainer;

import domain.Money;
import domain.RandomTicketFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Tickets {
    private static final int MIN_TICKET_SIZE = 0;
    public static final int TICKET_PRICE = 1000;

    private final List<Ticket> tickets;

    private Tickets(List<Ticket> tickets) {
        this.tickets = Collections.unmodifiableList(tickets);
    }

    private static List<Ticket> createRandomTickets(int randomTicketSize) {
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < randomTicketSize; i++) {
            tickets.add(RandomTicketFactory.createTicket());
        }
        return tickets;
    }

    public static Tickets createTickets(int totalTicketSize, int manualTicketSize, List<String> givenNumbers) {
        validateManualTicketSize(totalTicketSize, manualTicketSize);
        List<Ticket> tickets = toTickets(givenNumbers);
        int randomTicketSize = totalTicketSize - manualTicketSize;

        tickets.addAll(createRandomTickets(randomTicketSize));
        return new Tickets(tickets);
    }

    private static List<Ticket> toTickets(List<String> givenNumbers) {
        return givenNumbers.stream()
                .map(Ticket::new)
                .collect(Collectors.toList());
    }

    private static void validateManualTicketSize(int totalTicketSize, int manualTicketSize) {
        if (manualTicketSize < MIN_TICKET_SIZE) {
            throw new IllegalArgumentException(String.format("최소 %d장 이상 구매 가능합니다.", MIN_TICKET_SIZE));
        }
        if (manualTicketSize > totalTicketSize) {
            throw new IllegalArgumentException(String.format("최대 %d장 구매 가능합니다.", totalTicketSize));
        }
    }

    public static int getTotalTicketSize(Money money) {
        return money.getValue() / TICKET_PRICE;
    }

    @Override
    public String toString() {
        return tickets.stream()
                .map(Ticket::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    public List<Ticket> getValue() {
        return Collections.unmodifiableList(tickets);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tickets tickets1 = (Tickets) o;
        return Objects.equals(tickets, tickets1.tickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tickets);
    }
}