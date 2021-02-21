package lotto.ticket;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WinnerTicket {
    private static final String DELIMITER = ",";
    private static final String SPACE = " ";
    private static final String EMPTY = "";

    private final Ticket winnerTicket;

    public WinnerTicket(String numbers) {
        this.winnerTicket = splitNumbers(numbers);
    }

    private Ticket splitNumbers(String values) {
        List<Number> numbers = Stream.of(values.split(DELIMITER))
                .map(s -> s.replaceAll(SPACE, EMPTY))
                .map(Number::new)
                .collect(Collectors.toList());
        return new Ticket((numbers));
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