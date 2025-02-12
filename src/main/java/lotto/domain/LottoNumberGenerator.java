package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumberGenerator {

    private static final int LOTTO_START_NUMBER = 1;
    private static final int LOTTO_END_NUMBER = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;

    public static List<Integer> generate() {
        List<Integer> lottoNumbers = new ArrayList<>();
        for (int i = LOTTO_START_NUMBER; i <= LOTTO_END_NUMBER; i++) {
            lottoNumbers.add(i);
        }
        Collections.shuffle(lottoNumbers);

        return issue(lottoNumbers);
    }

    private static List<Integer> issue(List<Integer> lottoNumbers) {
        List<Integer> generatedNumber = new ArrayList<>();
        for (int i = 0; i < LOTTO_NUMBER_COUNT; i++) {
            generatedNumber.add(lottoNumbers.get(i));
        }
        return generatedNumber;
    }
}
