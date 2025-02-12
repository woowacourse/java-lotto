package domain;

import domain.numbergenerator.NumberGenerator;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    private Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public static Lotto from(NumberGenerator numberGenerator) {
        List<Integer> numbers = numberGenerator.generateNumber();
        return new Lotto(numbers);
    }
}
