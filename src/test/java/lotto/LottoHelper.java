package lotto;

import lotto.domain.ticket.ball.LottoBall;
import lotto.domain.ticket.ball.LottoBallFactory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoHelper {
    public static Set<LottoBall> lottoBalls(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoBallFactory::findLottoBallByNumber)
                .collect(Collectors.toSet());
    }
}
