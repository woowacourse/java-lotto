package lotto.domain.ticket.ball;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class LottoBallFactory {
    private static final List<LottoBall> ballInstance;
    private static final String NOT_EXIST_NUMBER_EXCEPTION_MESSAGE = "%d : 존재하지 않는 번호입니다.";

    static {
        ballInstance = IntStream.rangeClosed(1, 45)
                .mapToObj(LottoBall::from)
                .collect(Collectors.toList());
    }

    private LottoBallFactory() {
    }

    public static List<LottoBall> getInstance() {
        return ballInstance;
    }

    public static LottoBall findLottoBallByNumber(int number) {
        return ballInstance.stream()
                .filter(lottoBall -> lottoBall.isEqualNumber(number))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format(NOT_EXIST_NUMBER_EXCEPTION_MESSAGE, number)));
    }

    public static Set<LottoBall> collectLottoBalls(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoBallFactory::findLottoBallByNumber)
                .collect(Collectors.toSet());
    }
}
