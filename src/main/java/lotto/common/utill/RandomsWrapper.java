package lotto.common.utill;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public final class RandomsWrapper {
    private static final Random random = new Random();

    private RandomsWrapper() {
    }

    public static List<Integer> getRandomIntList(int min, int max, int size) {
        return Stream.generate(() -> random.nextInt(min, max))
            .limit(size)
            .toList();
    }
}
