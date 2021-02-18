package lotto.lottoticket;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WinnerTicket {
    private final Ticket winnerTicket;

    public WinnerTicket(String numbers) {
        this.winnerTicket = splitNumbers(numbers);
    }

    private Ticket splitNumbers(String values) {
        List<Number> numbers = Stream.of(values.split(","))
                .map(s -> s.replaceAll(" ", ""))
                .map(Number::new)
                .collect(Collectors.toList());
        return new Ticket((numbers));
    }

    public boolean isSameNumber(Number number) {
        return winnerTicket.contains(number);
    }

    public int findMatchCount(Ticket ticket) {
        return ticket.hasSameNumberCount(winnerTicket);
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