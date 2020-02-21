package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoBallFactory {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    private static List<LottoBall> lottoBalls;

    private LottoBallFactory() {
    }


    static {
        lottoBalls = IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                .mapToObj(LottoBall::new)
                .collect(Collectors
                        .toList());
    }

    public static List<LottoBall> getInstance() {
        return lottoBalls;
    }

    public static LottoBall findByLottoBall(int lottoBallNumber) {
        return lottoBalls.stream()
                .filter(x -> x.getLottoNumber() == lottoBallNumber)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
