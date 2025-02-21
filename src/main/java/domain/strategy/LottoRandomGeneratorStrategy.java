package domain.strategy;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class LottoRandomGeneratorStrategy implements LottoGenerateStrategy {

    private final int LOTTO_NUMBER_SIZE = 6;
    private final int LOTTO_NUMBER_START = 1;
    private final int LOTTO_NUMBER_END = 45;

    private final Random random;

    public LottoRandomGeneratorStrategy() {
        random = new Random();
    }

    @Override
    public List<Integer> generateNumbers() {
        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() != LOTTO_NUMBER_SIZE) {
            numbers.add(random.nextInt(LOTTO_NUMBER_START, LOTTO_NUMBER_END + 1));
        }
        return numbers.stream().toList();
    }
}
