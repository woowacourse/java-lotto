package domain.numbergenerator;

import java.util.List;

public class TestNumberGenerator implements NumberGenerator {
    private final List<Integer> toGenerateNumbers;

    public TestNumberGenerator(List<Integer> toGenerateNumbers) {
        this.toGenerateNumbers = toGenerateNumbers;
    }

    @Override
    public List<Integer> generateNumber() {
        return toGenerateNumbers;
    }
}
