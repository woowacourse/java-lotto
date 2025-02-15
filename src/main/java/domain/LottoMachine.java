package domain;

import domain.numberstrategy.NumberPickStrategy;
import java.util.HashSet;
import java.util.Set;

public class LottoMachine {

    private final NumberPickStrategy numberStrategy;

    public LottoMachine(NumberPickStrategy numberStrategy) {
        this.numberStrategy = numberStrategy;
    }

    public Lotto createLotto() {
        Set<Number> numbers = new HashSet<>();
        do {
            Number number = pickNumber();
            numbers.add(number);
        } while (numbers.size() != Lotto.NUMBERS_SIZE);
        return new Lotto(numbers);
    }

    private Number pickNumber() {
        int pickNumber = numberStrategy.pickNumber(Number.MIN_NUMBER, Number.MAX_NUMBER);
        return new Number(pickNumber);
    }
}
