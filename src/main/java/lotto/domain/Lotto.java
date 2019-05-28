package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto() {
        this.numbers = new ArrayList<>(LottoNumbers.getNumbers());
    }

    public Lotto(final List<Integer> numbers) {
        this.numbers = numbers;
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
}
