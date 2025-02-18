package domain;

import java.util.List;
import util.NumberGenerator;

public class MockNumberGenerator implements NumberGenerator {
    private final List<List<Integer>> predefinedNumbers;
    private int index = 0;

    public MockNumberGenerator(List<List<Integer>> predefinedNumbers) {
        this.predefinedNumbers = predefinedNumbers;
    }

    @Override
    public List<Integer> pickUniqueRandomNumbers(int min, int max, int count) {
        return predefinedNumbers.get(Math.min(index++, predefinedNumbers.size() - 1));
    }
}
