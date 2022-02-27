package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumberGenerator {

    private static final int MINIMUM_RANGE = 1;
    private static final int MAXIMUM_RANGE = 45;

    private final List<LottoNumber> lottoNumbers;

    public LottoNumberGenerator() {
        lottoNumbers = new ArrayList<>();
        for (int i = MINIMUM_RANGE; i <= MAXIMUM_RANGE; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
    }

    public List<LottoNumber> getLottoNumbers(int count) {
        shuffleNumbers();
        List<LottoNumber> numbers = lottoNumbers.subList(0, count);
        Collections.sort(numbers);
        return new ArrayList<>(numbers);
    }

    private void shuffleNumbers() {
        Collections.shuffle(lottoNumbers);
    }
}
