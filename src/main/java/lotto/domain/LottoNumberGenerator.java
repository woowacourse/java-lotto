package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumberGenerator {

    private final List<LottoNumber> lottoNumbers;

    public LottoNumberGenerator() {
        lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
    }

    private void shuffleNumbers() {
        Collections.shuffle(lottoNumbers);
    }

    public List<LottoNumber> getLottoNumbers(int count) {
        shuffleNumbers();
        List<LottoNumber> numbers = lottoNumbers.subList(0, count);
        Collections.sort(numbers);
        return new ArrayList<>(numbers);
    }
}
