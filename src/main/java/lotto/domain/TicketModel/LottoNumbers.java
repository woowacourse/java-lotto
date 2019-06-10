package lotto.domain.TicketModel;

import lotto.domain.Exceptions.ExceptionMessages;
import lotto.domain.Exceptions.LottoNumberException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LottoNumbers {
    private final List<Integer> numbers;

    LottoNumbers() {
        this.numbers = LottoNumberManager.autoNumber();
    }

    LottoNumbers(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (!LottoNumberManager.check(numbers)) {
            throw new LottoNumberException(ExceptionMessages.NUMBER.message());
        }
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    public List<Integer> rawNumbers() {
        return new ArrayList<>(numbers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumbers that = (LottoNumbers) o;
        return Objects.equals(numbers, that.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }
}