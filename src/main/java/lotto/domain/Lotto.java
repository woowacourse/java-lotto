package lotto.domain;

import java.util.ArrayList;
import java.util.List;

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
}
