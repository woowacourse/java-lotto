package generator;

import constans.ErrorType;
import java.util.List;

public abstract class NumberGenerator {

    final int startInclusive;
    final int endInclusive;

    public NumberGenerator(final int startInclusive, final int endInclusive) {
        validateStartLessThanEnd(startInclusive, endInclusive);
        this.startInclusive = startInclusive;
        this.endInclusive = endInclusive;
    }

    private void validateStartLessThanEnd(final int startInclusive, final int endInclusive) {
        if (startInclusive > endInclusive) {
            throw new IllegalArgumentException(ErrorType.NUMBER_GENERATOR_RANGE.getMessage());
        }
    }

    public abstract List<Integer> pickNumbersInRange(final int count);

    int generateRandomNumber() {
        return (int) (Math.random() * endInclusive + startInclusive);
    }
}
