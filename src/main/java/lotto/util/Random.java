package lotto.util;

import java.util.concurrent.ThreadLocalRandom;

public class Random implements Creatable {
    private static final int MINIMUM = 1;
    private static final int MAXIMUM = 45;

    @Override
    public int createRandomNumber() {
        return ThreadLocalRandom.current().nextInt(MINIMUM, MAXIMUM + 1);
    }
}
