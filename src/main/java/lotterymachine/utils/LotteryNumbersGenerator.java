package lotterymachine.utils;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static lotterymachine.utils.LotteryRule.*;

public class LotteryNumbersGenerator {
    public static List<Integer> generate() {
         return ThreadLocalRandom.current().ints(MINIMUM.getNumber(), MAXIMUM.getNumber())
                 .distinct().limit(TICKET_SIZE.getNumber()).boxed().collect(Collectors.toList());
    }
}
