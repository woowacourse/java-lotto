package lotterymachine.utils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotterymachine.vo.Ball;

public class LotteryNumbersGenerator {
    private static final List<Integer> preparedNumbers = IntStream.rangeClosed(1, 45).boxed()
            .collect(Collectors.toList());
    private static final int FIRST_NUMBER_INDEX = 0;
    private static final int LAST_NUMBER_INDEX = 6;

    public static List<Ball> generate() {
        Collections.shuffle(preparedNumbers);
        return Ball.createBalls(preparedNumbers.subList(FIRST_NUMBER_INDEX, LAST_NUMBER_INDEX));
    }
}
