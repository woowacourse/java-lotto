package lotterymachine.utils;


import lotterymachine.domain.LotteryNumber;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LotteryNumbersGenerator {
    private static final int MINIMUM_LOTTERY_NUMBER = 1;
    private static final int MAXIMUM_LOTTERY_NUMBER = 45;
    private static final int SIZE_OF_LOTTERY_TICKETS = 6;

    private static final List<Integer> numbers;

    static {
        numbers = IntStream.range(MINIMUM_LOTTERY_NUMBER, MAXIMUM_LOTTERY_NUMBER + 1)
                .boxed()
                .collect(Collectors.toList());
    }

    public static List<LotteryNumber> generate() {
        Collections.shuffle(numbers);
        return numbers.stream()
                .limit(SIZE_OF_LOTTERY_TICKETS)
                .map(LotteryNumber::valueOf)
                .collect(Collectors.toList());
    }
}
