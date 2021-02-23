package domain.ball;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static domain.ball.LottoBall.MAX_LOTTO_VALUE;
import static domain.ball.LottoBall.MIN_LOTTO_VALUE;

public class LottoBallFactory {
    static final List<LottoBall> lottoBalls;

    static {
        lottoBalls = IntStream.rangeClosed(MIN_LOTTO_VALUE, MAX_LOTTO_VALUE)
                .mapToObj(LottoBall::new)
                .collect(Collectors.toList());
    }

    private LottoBallFactory() {
    }

    public static LottoBallFactory getInstance() {
        return Holder.instance;
    }

    public List<LottoBall> getLottoBalls() {
        return new ArrayList<>(lottoBalls);
    }

    private static class Holder {
        static LottoBallFactory instance = new LottoBallFactory();
    }
}
