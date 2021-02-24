package lotto.util;

import lotto.domain.AutoLotto;
import lotto.domain.Lotto;
import lotto.domain.ManualLotto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class LottoFactory {
    private static final int START = 0;
    private static final int END = 6;
    private static final int MAXIMUM_CANDIDATE_NUMBER = 45;

    public LottoFactory() {
    }

    public abstract List<Lotto> getLottos();

    public static LottoFactory of(int count) {
        return new AutoLotto(Stream.generate(() -> new Lotto(makeLottoNumbers()))
                .limit(count)
                .collect(Collectors.toList()));

    }

    public static LottoFactory of(List<Lotto> manualLottos) {
        return new ManualLotto(manualLottos);
    }

    private static List<Integer> makeLottoNumbers() {
        List<Integer> numbers = Stream.iterate(1, n -> n + 1)
                .limit(MAXIMUM_CANDIDATE_NUMBER)
                .collect(Collectors.toList());
        Collections.shuffle(numbers);

        List<Integer> lottoNumber = numbers.subList(START, END);
        Collections.sort(lottoNumber);

        return lottoNumber;
    }



}
