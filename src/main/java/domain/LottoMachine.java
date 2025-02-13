package domain;

import domain.numberstrategy.NumberStrategy;
import java.util.HashSet;
import java.util.Set;

public class LottoMachine {

    public LottoMachine(NumberStrategy numberStrategy) {
        this.numberStrategy = numberStrategy;
    }

    private final NumberStrategy numberStrategy;


    public Lotto createLotto() {
        Set<Number> numbers = new HashSet<>();
        while (numbers.size() != Lotto.NUMBERS_SIZE) {
            numbers.add(selectNumber());
        }
        return new Lotto(numbers);
    }

    private Number selectNumber() {
        int randomValue = numberStrategy.pickNumber(Number.MAX_NUMBER);
        return new Number(randomValue);
    }
}
