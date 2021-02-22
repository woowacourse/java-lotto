package lotto.ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static lotto.game.LottoCount.ONE_COUNT;
import static lotto.game.LottoCount.ZERO;

public class Ticket {
    private final List<Number> numbers;

    public Ticket(List<Number> numbers) {
        this.numbers = new ArrayList<>(validate(numbers));
    }

    private List<Number> validate(List<Number> values) {
        TicketValidation.validateSize(values);
        TicketValidation.validateDuplicated(values);
        return values;
    }

    public int sameNumberCountOne(Number number) {
        if (numbers.contains(number)) {
            return ONE_COUNT;
        }
        return ZERO;
    }

    public boolean hasContainBonus(BonusBall bonusBall) {
        return numbers.stream()
                .anyMatch(bonusBall::isSameThan);
    }

    public boolean contains(Number value) {
        return numbers.stream()
                .anyMatch(value::equals);
    }

    public int sameNumberCount(Ticket winnerTicket) {
        int matchCount = ZERO;
        for (Number number : numbers) {
            matchCount += winnerTicket.sameNumberCountOne(number);
        }
        return matchCount;
    }

    public List<Number> getTicket() {
        return Collections.unmodifiableList(numbers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(numbers, ticket.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }
}
