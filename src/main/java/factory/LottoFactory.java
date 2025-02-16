package factory;

import java.util.List;
import model.Lotto;
import util.RandomGenerator;

public class LottoFactory {
    public static Lotto createCustomLotto(List<Integer> numbers) {
        return new Lotto(numbers);
    }

    public static Lotto createRandomLotto() {
        List<Integer> randomNumbers = RandomGenerator.generateNumbers(1, 45, 6);
        return new Lotto(randomNumbers);
    }
}
