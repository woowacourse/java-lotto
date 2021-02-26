package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoFactory {
    private static final List<LottoNumber> CANDIDATES_NUMBERS;

    static {
        CANDIDATES_NUMBERS = IntStream.rangeClosed(LottoNumber.NUMBER_MIN, LottoNumber.NUMBER_MAX)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
    }

    public static Lottos generates(ShuffleStrategy strategy, int lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(generate(strategy));
        }

        return new Lottos(lottos);
    }

    private static Lotto generate(ShuffleStrategy strategy) {
        List<LottoNumber> shuffled = strategy.shuffle(CANDIDATES_NUMBERS);
        return new Lotto(shuffled.subList(0, Lotto.LENGTH));
    }
}
