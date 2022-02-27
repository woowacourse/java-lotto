package model.lotto;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class LottoResponse {
    private final Set<Integer> numbers;

    public LottoResponse(Set<Integer> numbers) {
        this.numbers = new HashSet<>(numbers);
    }

    public Set<Integer> getNumbers() {
        return Collections.unmodifiableSet(numbers);
    }
}
