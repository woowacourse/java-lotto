package lotto.domain.generator;

import lotto.domain.LottoNumber;
import lotto.domain.factory.LottoNumberFactory;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomNumberGenerator implements NumberGenerator {
    private static final int LOTTO_INDEX_INIT = 0;
    private static final int LOTTO_NUMBER_COUNT = 6;

    @Override
    public List<LottoNumber> generateNumbers() {
        long seed = System.nanoTime();

        List<LottoNumber> randomNumbers = LottoNumberFactory.createLottoNumberList();
        Collections.shuffle(randomNumbers, new Random(seed));
        return randomNumbers.subList(LOTTO_INDEX_INIT, LOTTO_NUMBER_COUNT);
    }

    @Override
    public List<LottoNumber> generateNumbers(String Input) {
        return Collections.emptyList();
    }
}
