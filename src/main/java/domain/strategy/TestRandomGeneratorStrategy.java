package domain.strategy;

import java.util.List;

public class TestRandomGeneratorStrategy implements LottoGenerateStrategy {

    private final List<Integer> numbers;

    public TestRandomGeneratorStrategy(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public List<Integer> generateNumbers() {
        return numbers;
    }
}
