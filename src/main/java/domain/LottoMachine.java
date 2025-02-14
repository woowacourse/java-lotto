package domain;

import domain.numberstrategy.NumberPickStrategy;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoMachine {

    public LottoMachine(NumberPickStrategy numberStrategy) {
        this.numberStrategy = numberStrategy;
    }

    private final NumberPickStrategy numberStrategy;


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
