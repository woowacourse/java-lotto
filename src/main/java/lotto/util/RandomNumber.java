package lotto.util;

import java.util.Random;

public class RandomNumber implements NumberGenerator {
    private final Random random = new Random();

    @Override
    public int generate(int start, int end) {
        return random.nextInt(end - start + 1) + start;
    }

}