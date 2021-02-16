package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoFactory {
    private static final List<Integer> CANDIDATES_NUMBERS;

    static {
        CANDIDATES_NUMBERS = IntStream.rangeClosed(Lotto.NUMBER_MIN, Lotto.NUMBER_MAX)
                .mapToObj(Integer::new)
                .collect(Collectors.toList());
    }

    public static List<Lotto> generates(ShuffleStrategy strategy, int lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(generate(strategy));
        }

        return lottos;
    }

    private static Lotto generate(ShuffleStrategy strategy) {
        List<Integer> shuffled = strategy.shuffle(CANDIDATES_NUMBERS);
        return new Lotto(shuffled.subList(0, Lotto.LENGTH));
    }
}
