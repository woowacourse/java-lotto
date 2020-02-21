package lotto.domain.ticket.ball;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoBallFactory {
    private static final Map<Integer, LottoBall> instance;

    static {
        instance = IntStream.rangeClosed(1, 45)
                .mapToObj(LottoBall::from)
                .collect(Collectors.toMap(LottoBall::getNumber, Function.identity()));
    }

    public static List<LottoBall> getInstance() {
        return new ArrayList<>(instance.values());
    }

    public static LottoBall findLottoBallByNumber(int number) {
        return instance.get(number);
    }

    public static Set<LottoBall> collectLottoBalls(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoBallFactory::findLottoBallByNumber)
                .collect(Collectors.toSet());
    }
}
