package lotto.lottoticket;

import lotto.lottoticket.ticketnumber.NumbersGenerator;

import java.util.List;
import java.util.Objects;

import static lotto.lottogame.LottoCount.ONE_COUNT;
import static lotto.lottogame.LottoCount.ZERO;

public class Ticket {
    private final List<Integer> numbers;

    public Ticket(NumbersGenerator numbersGenerator) {
        this.numbers = validate(numbersGenerator.generate());
    }

    private List<Integer> validate(List<Integer> values) {
        TicketValidation.validateSize(values);
        TicketValidation.validateDuplicated(values);
        for (Integer number : values) {
            TicketValidation.validateNumberInRange(number);
        }
        return values;
    }

    public int hasSameNumber(Integer number) {
        if (numbers.contains(number)) {
            return ONE_COUNT;
        }
        return ZERO;
    }

    public boolean hasContainBonus(BonusBall bonusBall) {
        return numbers.stream()
                .anyMatch(bonusBall::isSameThan);
    }

    public List<Integer> getTicket() {
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
