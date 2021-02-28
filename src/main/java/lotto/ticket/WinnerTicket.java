package lotto.ticket;

import lotto.ticket.util.SplitNumbers;

import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;

public class WinnerTicket {
    private final Ticket winnerTicket;

    public WinnerTicket(String numbers) {
        this.winnerTicket = splitNumbers(numbers);
    }

    private Ticket splitNumbers(String values) {
        return SplitNumbers.splitNumberStream(values)
                .collect(collectingAndThen(Collectors.toList(), Ticket::new));
    }

    public boolean isSameNumber(Number number) {
        return winnerTicket.contains(number);
    }

    public int findMatchCount(Ticket ticket) {
        return ticket.sameNumberCount(winnerTicket);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinnerTicket that = (WinnerTicket) o;
        return Objects.equals(winnerTicket, that.winnerTicket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winnerTicket);
    }
}