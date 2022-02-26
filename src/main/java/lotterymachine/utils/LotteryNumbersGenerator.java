package lotterymachine.utils;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class LotteryNumbersGenerator {
    private static final int MINIMUM_LOTTERY_NUMBER = 1;
    private static final int MAXIMUM_LOTTERY_NUMBER = 45;
    private static final int SIZE_OF_LOTTERY_TICKETS = 6;

    public static List<Integer> generate() {
         return ThreadLocalRandom.current()
                 .ints(MINIMUM_LOTTERY_NUMBER, MAXIMUM_LOTTERY_NUMBER)
                 .distinct()
                 .limit(SIZE_OF_LOTTERY_TICKETS)
                 .boxed()
                 .collect(Collectors.toList());
    }
}
