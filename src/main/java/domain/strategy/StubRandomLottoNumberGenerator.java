package domain.strategy;

import java.util.List;

public class StubRandomLottoNumberGenerator implements LottoNumberGenerateStrategy {
    private final List<Integer> numbers;

    public StubRandomLottoNumberGenerator(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public List<Integer> generateLottoNumbers() {
        return this.numbers;
    }
}
