package domain.strategy;

import constants.LottoConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLottoNumberGenerateStrategy implements LottoNumberGenerateStrategy {
    private static final int RANDOM_LOTTO_NUMBER_START_INDEX = 0;
    private static final int RANDOM_LOTTO_NUMBER_END_INDEX = 6;

    @Override
    public List<Integer> generateLottoNumbers() {
        List<Integer> modifiableLottoNumbers = new ArrayList<>(LottoConstants.LOTTO_NUMBERS);
        Collections.shuffle(modifiableLottoNumbers);

        return Collections.unmodifiableList(
                modifiableLottoNumbers.subList(RANDOM_LOTTO_NUMBER_START_INDEX, RANDOM_LOTTO_NUMBER_END_INDEX));
    }
}
