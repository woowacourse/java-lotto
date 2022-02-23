package domain.strategy;

import constants.LottoNumbers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLottoNumberGenerateStrategy implements LottoNumberGenerateStrategy {
    private static final int WINNING_NUMBER_START_INDEX = 0;
    private static final int WINNING_NUMBER_END_INDEX = 6;

    @Override
    public List<Integer> generateWinningNumbers() {
        List<Integer> modifiableLottoNumbers = new ArrayList<>(LottoNumbers.LOTTO_NUMBERS);
        Collections.shuffle(modifiableLottoNumbers);

        return Collections.unmodifiableList(
                modifiableLottoNumbers.subList(WINNING_NUMBER_START_INDEX, WINNING_NUMBER_END_INDEX));
    }
}
