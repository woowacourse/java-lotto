package lotto.domain.generator;

import lotto.domain.LottoNo;

import java.util.Collections;
import java.util.List;

public class ShuffleRandomStrategy implements ShuffleStrategy {
    private static ShuffleRandomStrategy instance = new ShuffleRandomStrategy();

    private ShuffleRandomStrategy() {
    }

    public static ShuffleRandomStrategy getInstance() {
        return instance;
    }

    @Override
    public List<LottoNo> shuffle(final List<LottoNo> lottoNos) {
        Collections.shuffle(lottoNos);
        return lottoNos;
    }
}
