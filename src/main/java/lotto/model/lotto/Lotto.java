package lotto.model.lotto;

import java.util.List;
import lotto.model.lotto.generator.NumberGenerator;
import lotto.model.lotto.vo.LottoNumbers;

public class Lotto {

    private static final int MAX_NUMBER_SIZE = 6;

    private final LottoNumbers numbers;

    private Lotto(LottoNumbers numbers) {
        this.numbers = numbers;
    }

    public static Lotto generateFrom(NumberGenerator numberGenerator) {
        return new Lotto(LottoNumbers.generateFrom(numberGenerator, MAX_NUMBER_SIZE));
    }

    public List<Integer> getNumbers() {
        return numbers.getValues();
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    @Override
    public String toString() {
        return "Lotto{" +
                "numbers=" + numbers +
                '}';
    }
}
