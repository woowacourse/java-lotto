package domain;

import domain.numbergenerator.NumberGenerator;
import java.util.HashSet;
import java.util.Set;

public class LottoMachine {

    private final NumberGenerator numberGenerator;
    private final int numbersMaxSize;

    public LottoMachine(NumberGenerator numberGenerator, int numbersMaxSize) {
        this.numberGenerator = numberGenerator;
        this.numbersMaxSize = numbersMaxSize;
    }

    public Lotto createLotto() {
        Set<Number> numbers = new HashSet<>();
        do {
            Number number = pickNumber();
            numbers.add(number);
        } while (numbers.size() != numbersMaxSize);
        return new Lotto(numbers);
    }

    private Number pickNumber() {
        int pickNumber = numberGenerator.generate();
        return new Number(pickNumber);
    }
}
