package lotto.domain;

import lotto.domain.Exceptions.LottoNumberDuplicateException;
import lotto.domain.Exceptions.LottoNumberException;

import java.util.*;

public class Lotto implements LottoTicket {
    public static final int NUMBER_COUNT = 6;
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 45;
    private final List<Integer> numbers;

    public Lotto(final List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != NUMBER_COUNT) {
            throw new LottoNumberException();
        }

        if (new HashSet<Integer>(numbers).size() != numbers.size()) {
            throw new LottoNumberDuplicateException();
        }

        if (Collections.min(numbers) < MIN_NUMBER || Collections.max(numbers) > MAX_NUMBER) {
            throw new LottoNumberException();
        }
    }

    public List<Integer> numbers() {
        return this.numbers;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(this.numbers, lotto.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.numbers);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
