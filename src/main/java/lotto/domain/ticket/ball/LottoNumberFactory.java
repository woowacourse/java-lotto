package lotto.domain.ticket.ball;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class LottoNumberFactory {
    private static final List<LottoNumber> lottoNumberInstance;

    static {
        lottoNumberInstance = IntStream.rangeClosed(1, 45)
                .mapToObj(LottoNumber::from)
                .collect(Collectors.toList());
    }

    private LottoNumberFactory() {
    }

    public static List<LottoNumber> getInstance() {
        return lottoNumberInstance;
    }

    public static LottoNumber findLottoBallByNumber(int number) {
        return lottoNumberInstance.stream()
                .filter(lottoBall -> lottoBall.isEqualNumber(number))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("%d : 존재하지 않는 번호입니다.", number)));
    }

}
