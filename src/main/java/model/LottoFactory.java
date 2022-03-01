package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoFactory {
    private static final LottoFactory LOTTO_FACTORY = new LottoFactory();

    private LottoFactory() {

    }

    public static LottoFactory getInstance() {
        return LOTTO_FACTORY;
    }

    public Lotto generateManual(List<Integer> manualNumbers) {
        return Lotto.from(manualNumbers.stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toUnmodifiableList()));
    }

    public Lotto generateAuto() {
        List<LottoNumber> lottoNumberPool = new ArrayList<>(LottoNumber.LOTTO_NUMBER_POOL.values());
        Collections.shuffle(lottoNumberPool);
        return Lotto.from(new ArrayList<>(lottoNumberPool.subList(0, 6)));
    }
}
