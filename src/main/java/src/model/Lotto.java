package src.model;

import src.model.generator.NumberGenerator;
import src.model.vo.LottoNumbers;

public class Lotto {

    private static final int MAX_NUMBER_SIZE = 6;

    private final LottoNumbers numbers;

    private Lotto(LottoNumbers numbers) {
        this.numbers = numbers;
    }

    public static Lotto generateFrom(NumberGenerator numberGenerator) {
        return new Lotto(LottoNumbers.generateFrom(numberGenerator, MAX_NUMBER_SIZE));
    }

    @Override
    public String toString() {
        return "Lotto{" +
                "numbers=" + numbers +
                '}';
    }
}
