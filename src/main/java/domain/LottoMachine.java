package domain;

import java.util.HashSet;
import java.util.Set;

public class LottoMachine {

    public Lotto createLotto() {
        Set<Number> numbers = new HashSet<>();
        while (numbers.size() != Lotto.NUMBERS_SIZE) {
            numbers.add(selectNumber());
        }
        return new Lotto(numbers);
    }

    private Number selectNumber() {
        int randomValue = (int) (Math.random() * 45) + 1;
        return new Number(randomValue);

    }
}
