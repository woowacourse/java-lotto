package lotto.domain;

import lotto.Exception.NotFindByLottoBallException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoBallFactory {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    public static final String NOT_FIND_BY_LOTTO_BALL_ERROR_MESSAGE = "찾는 로또볼이 없습니다. 다시 시도해주세요.";

    private static List<LottoBall> lottoBalls;

    static {
        lottoBalls = IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                .mapToObj(LottoBall::new)
                .collect(Collectors
                        .toList());
    }

    private LottoBallFactory() {
    }

    public static List<LottoBall> getInstance() {
        return lottoBalls;
    }

    public static LottoBall findByLottoBall(int lottoBallNumber) {
        return lottoBalls.stream()
                .filter(x -> x.getLottoNumber() == lottoBallNumber)
                .findFirst()
                .orElseThrow(() -> new NotFindByLottoBallException(NOT_FIND_BY_LOTTO_BALL_ERROR_MESSAGE));
    }
}