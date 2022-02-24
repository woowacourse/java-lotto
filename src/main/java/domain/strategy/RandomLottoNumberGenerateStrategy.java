package domain.strategy;

import constants.LottoNumbers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLottoNumberGenerateStrategy implements LottoNumberGenerateStrategy {
    private static final int RANDOM_LOTTO_NUMBER_START_INDEX = 0;
    private static final int RANDOM_LOTTO_NUMBER_END_INDEX = 6;

    @Override
    public List<Integer> generateLottoNumbers() {
        List<Integer> lottoNumbers = new ArrayList<>(LottoNumbers.LOTTO_NUMBERS);
        Collections.shuffle(lottoNumbers);
        List<Integer> shuffled = lottoNumbers.subList(RANDOM_LOTTO_NUMBER_START_INDEX, RANDOM_LOTTO_NUMBER_END_INDEX);
        Collections.sort(shuffled);

        return Collections.unmodifiableList(shuffled);
    }
}
