package domain.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLottoNumberGenerateStrategy implements LottoNumberGenerateStrategy {
    private static final int RANDOM_LOTTO_NUMBER_START_INDEX = 0;
    private static final int RANDOM_LOTTO_NUMBER_END_INDEX = 6;
    private static final List<Integer> lottoNumbers;

    static {
        lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            lottoNumbers.add(i);
        }
    }

    @Override
    public List<Integer> generateLottoNumbers() {
        Collections.shuffle(lottoNumbers);
        List<Integer> pickedNumbers = lottoNumbers.subList(RANDOM_LOTTO_NUMBER_START_INDEX,
                RANDOM_LOTTO_NUMBER_END_INDEX);
        Collections.sort(pickedNumbers);

        return Collections.unmodifiableList(pickedNumbers);
    }
}
