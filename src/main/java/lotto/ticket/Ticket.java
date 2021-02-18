package lotto.ticket;

import lotto.ticket.strategy.NumbersGenerator;

import java.util.List;
import java.util.Objects;

import static lotto.game.LottoCount.ONE_COUNT;
import static lotto.game.LottoCount.ZERO;

public class Ticket {
    private final List<Number> numbers;

    public Ticket(NumbersGenerator numbersGenerator) {
        this.numbers = validate(numbersGenerator.generate());
    }

    public Ticket(List<Number> numbers) {
        this.numbers = validate(numbers);
    }

    private List<Number> validate(List<Number> values) {
        TicketValidation.validateSize(values);
        TicketValidation.validateDuplicated(values);
        return values;
    }

    public int hasSameNumber(Number number) {
        if (numbers.contains(number)) {
            return ONE_COUNT;
        }
        return ZERO;
    }

    public boolean hasContainBonus(BonusBall bonusBall) {
        for (Number number : numbers) {
            if (bonusBall.isSameThan(number)) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(Number value) {
        for (Number number : numbers) {
            if (number.equals(value)) {
                return true;
            }
        }
        return false;
    }

    public int hasSameNumberCount(Ticket winnerTicket) {
        int matchCount = ZERO;
        for (Number number : numbers) {
            matchCount += winnerTicket.hasSameNumber(number);
        }
        return matchCount;
    }

    public List<Number> getTicket() {
        return numbers;
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
