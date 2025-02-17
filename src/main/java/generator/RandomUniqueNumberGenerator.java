package generator;

import constants.ErrorType;
import java.util.ArrayList;
import java.util.List;

public class RandomUniqueNumberGenerator implements NumberGenerator {

    final int startInclusive;
    final int endInclusive;

    public RandomUniqueNumberGenerator(final int startInclusive, final int endInclusive) {
        validateStartLessThanEnd(startInclusive, endInclusive);
        this.startInclusive = startInclusive;
        this.endInclusive = endInclusive;
    }

    private void validateStartLessThanEnd(final int startInclusive, final int endInclusive) {
        if (startInclusive > endInclusive) {
            throw new IllegalArgumentException(ErrorType.NUMBER_GENERATOR_RANGE.getMessage());
        }
    }

    @Override
    public List<Integer> generateNumbers(final int count) {
        validateCount(count);
        final List<Integer> randoms = new ArrayList<>();
        while (randoms.size() < count) {
            int randomNumber = generateRandomNumber();
            if (!randoms.contains(randomNumber)) {
                randoms.add(randomNumber);
            }
        }

        return randoms;
    }

    private void validateCount(final int count) {
        final int rangeCount = this.endInclusive - this.startInclusive + 1;
        if (rangeCount < count) {
            throw new IllegalArgumentException(ErrorType.RANDOM_NUMBER_GENERATOR_COUNT.getMessage());
        }

    }

    int generateRandomNumber() {
        return (int) (Math.random() * endInclusive + startInclusive);
    }
}
