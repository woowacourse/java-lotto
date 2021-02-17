package lotto.lottoticket;

import java.util.List;
import java.util.Objects;

public class Ticket {
    private final List<Integer> numbers;

    public Ticket(NumbersGenerator numbersGenerator) {
        this.numbers = validate(numbersGenerator.generate());
    }

    private List<Integer> validate(List<Integer> values) {
        TicketValidation.validateSize(values);
        for (Integer number : values) {
            TicketValidation.validateNumberInRange(number);
        }
        TicketValidation.validateDuplicated(values);
        return values;
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
