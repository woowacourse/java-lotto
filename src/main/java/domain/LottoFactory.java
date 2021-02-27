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

    public static Lottos generatesPassiveLottos(List<Lotto> lottos) { //todo: 수동로또도 캐싱으로 처리할 방법 없을까? (Hint: Integer 뜯어보기!)
        return new Lottos(lottos);
    }

    public static Lottos generateAutoLottos(ShuffleStrategy strategy, int numberOfLotto) {
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
