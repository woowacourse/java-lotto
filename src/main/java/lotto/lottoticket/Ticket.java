package lotto.lottoticket;

import java.util.List;
import java.util.Objects;

import static lotto.lottoticket.RandomNumbersGenerator.*;

public class Ticket {
    private final List<Integer> numbers;

    public Ticket(NumbersGenerator numbersGenerator) {
        this.numbers = validate(numbersGenerator.generate());
    }

    private List<Integer> validate(List<Integer> values) {
        checkSizeOfNumbers(values);
        for (Integer number : values) {
            checkNumberInRange(number);
        }
        checkDuplicatedNumber(values);
        return values;
    }

    private void checkSizeOfNumbers(List<Integer> values) {
        if (values.size() != NUMBER_COUNT_IN_LOTTO) {
            throw new IllegalArgumentException("숫자는 6개여야 합니다.");
        }
    }

    private void checkDuplicatedNumber(List<Integer> values) {
        boolean duplicated = values.stream()
                .distinct()
                .count() != values.size();

        if (duplicated) {
            throw new IllegalArgumentException("중복되는 숫자가 생성되었습니다.");
        }
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
