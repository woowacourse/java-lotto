package domain.strategy;

import domain.LottoNumber;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class RandomLottoNumberGenerator implements LottoNumberGenerateStrategy {
    private static final int RANDOM_LOTTO_NUMBER_START_INDEX = 0;
    private static final int RANDOM_LOTTO_NUMBER_END_INDEX = 6;

    @Override
    public Set<Integer> generateLottoNumbers() {
        List<Integer> lottoNumbers = new ArrayList<>(LottoNumber.LOTTO_NUMBERS);
        Collections.shuffle(lottoNumbers);

        return Set.copyOf(
                lottoNumbers.subList(RANDOM_LOTTO_NUMBER_START_INDEX, RANDOM_LOTTO_NUMBER_END_INDEX)
        );
    }
}
