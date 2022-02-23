package lotterymachine.utils;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class LotteryNumbersGenerator {
    private static final int LOTTERY_MINIMUM_NUMBER = 1;
    private static final int LOTTERY_MAXIMUM_NUMBER = 46;
    private static final int LOTTERY_TICKET_SIZE = 6;

    public static List<Integer> generate() {
         return ThreadLocalRandom.current().ints(LOTTERY_MINIMUM_NUMBER, LOTTERY_MAXIMUM_NUMBER)
                 .distinct().limit(LOTTERY_TICKET_SIZE).boxed().collect(Collectors.toList());
    }
}
