package lotto.domain.generator;

import lotto.domain.Lotto;
import lotto.domain.LottoNo;
import lotto.domain.LottoNoCollection;

import java.util.List;

public class LottoNosAutoGenerator implements LottoNosGenerator {
    private ShuffleStrategy shuffleStrategy;

    public LottoNosAutoGenerator() {
        this(ShuffleRandomStrategy.getInstance());
    }

    public LottoNosAutoGenerator(final ShuffleStrategy shuffleStrategy) {
        this.shuffleStrategy = shuffleStrategy;
    }

    @Override
    public List<LottoNo> generate() {
        List<LottoNo> lottoNos = LottoNoCollection.createLottoNos();
        List<LottoNo> shuffle = shuffleStrategy.shuffle(lottoNos);
        return shuffle.subList(0, Lotto.LOTTO_NO_SIZE);
    }
}
