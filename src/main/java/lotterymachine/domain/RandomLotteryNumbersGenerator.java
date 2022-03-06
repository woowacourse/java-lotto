package lotterymachine.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLotteryNumbersGenerator implements LotteryNumbersGenerator {
    private static final List<Integer> NUMBERS;

    static {
        NUMBERS = IntStream.rangeClosed(LotteryNumber.MIN_VALUE, LotteryNumber.MAX_VALUE)
                .boxed()
                .collect(Collectors.toList());
    }

    @Override
    public List<LotteryNumber> generate() {
        Collections.shuffle(NUMBERS);
        return NUMBERS.stream()
                .limit(LotteryTicket.TICKET_SIZE)
                .map(LotteryNumber::from)
                .collect(Collectors.toList());
    }
}