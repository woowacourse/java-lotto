package domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LottoFactory {
    private static final List<LottoNumber> CANDIDATES_NUMBERS;

    static {
        CANDIDATES_NUMBERS = IntStream.rangeClosed(LottoNumber.NUMBER_MIN, LottoNumber.NUMBER_MAX)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
    }

    public static Lottos generates(ShuffleStrategy strategy, int lottoCount) {
        List<Lotto> lottos = Stream.generate(() -> generate(strategy))
                .limit(lottoCount)
                .collect(Collectors.toList());

        return new Lottos(lottos);
    }

    private static Lotto generate(ShuffleStrategy strategy) {
        List<LottoNumber> shuffledLottoNumbers = strategy.shuffle(CANDIDATES_NUMBERS);
        return new Lotto(shuffledLottoNumbers.subList(0, Lotto.LENGTH));
    }
}
