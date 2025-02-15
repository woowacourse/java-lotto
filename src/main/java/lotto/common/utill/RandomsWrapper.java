package lotto.common.utill;

import static lotto.common.constant.Constant.*;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public final class RandomsWrapper {
    private static final Random random = new Random();

    private RandomsWrapper() {
    }

    public static List<Integer> getRandomIntList() {
        return Stream.generate(() -> random.nextInt(LOTTO_MINIMUM, LOTTO_MAXIMUM))
            .distinct()
            .limit(LOTTO_SIZE)
            .toList();
    }
}
