package lotto.domain.ticket.ball;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lotto.domain.ticket.ball.LottoBallValidator.LOWER_BOUND;
import static lotto.domain.ticket.ball.LottoBallValidator.UPPER_BOUND;

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

    public static LottoBall getLottoBallByNumber(int number) {
        LottoBallValidator.validateNumber(number);
        return instance.get(number);
    }
}
