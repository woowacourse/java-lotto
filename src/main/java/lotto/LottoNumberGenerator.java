package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumberGenerator {

    private final List<Integer> lottoNumbers;

    public LottoNumberGenerator() {
        lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            lottoNumbers.add(i);
        }
    }

    private void shuffleNumbers() {
        Collections.shuffle(lottoNumbers);
    }

    public List<Integer> getLottoNumbers(int count) {
        shuffleNumbers();
        return lottoNumbers.subList(0, count);
    }
}
