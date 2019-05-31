package lotto.domain;

import java.util.*;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(final List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }

        if (new HashSet<Integer>(numbers).size() != numbers.size()) {
            throw new IllegalArgumentException();
        }

        if (Collections.min(numbers) < 1 || Collections.max(numbers) > 45) {
            throw new IllegalArgumentException();
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
        return "numbers=" + numbers;
    }
}
