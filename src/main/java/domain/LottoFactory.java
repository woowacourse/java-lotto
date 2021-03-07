package domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LottoFactory {
    private static final int ZERO = 0;
    private static final List<LottoNumber> CANDIDATES_NUMBERS;

    static {
        CANDIDATES_NUMBERS = IntStream.rangeClosed(LottoNumber.NUMBER_MIN, LottoNumber.NUMBER_MAX)
                .mapToObj(LottoNumber::of)
                .collect(Collectors.toList());
    }

    public static Lottos generatesPassiveLottos(List<Lotto> lottos) {
        return new Lottos(lottos);
    }

    public static Lottos generateAutoLottos(ShuffleStrategy strategy, int numberOfLotto) {
        if (numberOfLotto < ZERO) {
            throw new IllegalArgumentException("입력한 로또 개수가 총 구매할 로또 개수를 초과하였습니다.");
        }

        List<Lotto> lottos = Stream.generate(() -> generateAutoLotto(strategy))
                .limit(numberOfLotto)
                .collect(Collectors.toList());
        return new Lottos(lottos);
    }

    private static Lotto generateAutoLotto(ShuffleStrategy strategy) {
        List<LottoNumber> shuffledLottoNumbers = strategy.shuffle(CANDIDATES_NUMBERS);
        return new Lotto(shuffledLottoNumbers.subList(0, Lotto.LENGTH));
    }
}
