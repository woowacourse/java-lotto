package lotto.lottoticket;

import java.util.List;
import java.util.Objects;

import static lotto.lottoticket.RandomNumbersGenerator.MAXIMUM_NUMBER;
import static lotto.lottoticket.RandomNumbersGenerator.MINIMUM_NUMBER;

public class Ticket {
    private final List<Integer> numbers;

    public Ticket(NumbersGenerator numbersGenerator) {
        this.numbers = validate(numbersGenerator.generate());
    }

    private List<Integer> validate(List<Integer> values) {
        for (Integer number : values) {
            checkNumberInRange(number);
        }
        return values;
    }

    private void checkNumberInRange(Integer number) {
        if (number < MINIMUM_NUMBER || number > MAXIMUM_NUMBER) {
            throw new IllegalArgumentException("옳지 않은 숫자가 생성되었습니다.");
        }
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
