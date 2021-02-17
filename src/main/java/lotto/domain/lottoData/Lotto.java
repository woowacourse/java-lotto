package lotto.domain.lottoData;

import java.util.*;

public class Lotto {
    final List<Integer> numbers;

    public Lotto(List<Integer> values) {
        numbers = values;
    }

    public List<Integer> values() {
        return Collections.unmodifiableList(numbers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(numbers, lotto.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }
}
