package domain;

import domain.lottogeneratestrategy.LottoPickStrategy;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoMachine {

    public LottoMachine(LottoPickStrategy numberStrategy) {
        this.numberStrategy = numberStrategy;
    }

    private final LottoPickStrategy numberStrategy;

    public Lotto createLotto() {
        Set<Number> numbers;
        do {
            numbers = new HashSet<>(selectNumbers());
        } while (numbers.size() != Lotto.NUMBERS_SIZE);
        return new Lotto(numbers);
    }

    private List<Number> selectNumbers() {
        List<Integer> numbers = numberStrategy.pickNumbers(Number.MAX_NUMBER, Lotto.NUMBERS_SIZE);
        return numbers.stream().map(Number::new).toList();
    }
}
