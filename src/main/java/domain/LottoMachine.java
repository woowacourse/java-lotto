package domain;

import domain.numberstrategy.NumberGenerator;
import java.util.HashSet;
import java.util.Set;

public class LottoMachine {

    private final NumberGenerator numberGenerator;
    private final int numbersSize;

    public LottoMachine(NumberGenerator numberGenerator, int numbersSize) {
        this.numberGenerator = numberGenerator;
        this.numbersSize = numbersSize;
    }

    public Lotto createLotto() {
        Set<Number> numbers = new HashSet<>();
        do {
            Number number = pickNumber();
            numbers.add(number);
        } while (numbers.size() != numbersSize);
        return new Lotto(numbers);
    }

    private Number pickNumber() {
        int pickNumber = numberGenerator.generate();
        return new Number(pickNumber);
    }
}
