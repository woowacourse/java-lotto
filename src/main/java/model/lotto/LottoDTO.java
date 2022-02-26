package model.lotto;

import java.util.*;

public class LottoDTO {
    private final Set<Integer> numbers;

    public LottoDTO(Set<Integer> numbers) {
        this.numbers = new HashSet<>(numbers);
    }

    public Set<Integer> getNumbers() {
        return Collections.unmodifiableSet(numbers);
    }
}
