package lotto.domain.ticket.ball;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lotto.domain.ticket.ball.LottoBall.LOWER_BOUND;
import static lotto.domain.ticket.ball.LottoBall.UPPER_BOUND;

public class LottoFactory {
    private static final Map<Integer, LottoBall> instance;

    static {
        instance = IntStream.rangeClosed(LOWER_BOUND, UPPER_BOUND)
                .mapToObj(LottoBall::new)
                .collect(Collectors.toMap(LottoBall::getNumber, Function.identity()));
    }

    public static List<LottoBall> getInstance() {
        return new ArrayList<>(instance.values());
    }

    public static LottoBall findLottoBallByNumber(int number) {
        return instance.get(number);
    }
}
