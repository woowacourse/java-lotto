package domain.numbergenerator;

import java.util.List;

public class FakeNumberGenerator implements NumberGenerator {
    private final List<Integer> numbers;

    public FakeNumberGenerator(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public List<Integer> generateNumber() {
        return numbers;
    }
}
