package generator;

import constans.ErrorType;
import java.util.ArrayList;
import java.util.List;

public class RandomUniqueNumberGenerator extends NumberGenerator {

    public RandomUniqueNumberGenerator(
        final int startInclusive,
        final int endInclusive
    ) {
        super(startInclusive, endInclusive);
    }

    @Override
    public List<Integer> pickNumbersInRange(final int count) {
        validateCount(count);
        return generateUniqueRandomNumbers(count);
    }

    private List<Integer> generateUniqueRandomNumbers(final int count) {
        final List<Integer> randoms = new ArrayList<>();
        while (randoms.size() < count) {
            addUniqueRandomNumber(randoms);
        }
        return randoms;
    }

    private void addUniqueRandomNumber(final List<Integer> randoms) {
        int randomNumber = super.generateRandomNumber();
        if (!randoms.contains(randomNumber)) {
            randoms.add(randomNumber);
        }
    }

    private void validateCount(final int count) {
        final int rangeCount = super.endInclusive - super.startInclusive + 1;
        if (rangeCount < count) {
            throw new IllegalArgumentException(ErrorType.RANDOM_NUMBER_GENERATOR_COUNT.getMessage());
        }

    }
}
