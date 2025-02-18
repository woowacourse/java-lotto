package lotto.domain;

import lotto.domain.constant.LottoConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumberGenerator {

    private LottoNumberGenerator() {

    }

    public static List<Integer> generate() {
        List<Integer> lottoNumbers = new ArrayList<>();
        for (int i = LottoConstants.MIN_NUMBER; i <= LottoConstants.MAX_NUMBER; i++) {
            lottoNumbers.add(i);
        }
        Collections.shuffle(lottoNumbers);

        return issue(lottoNumbers);
    }

    private static List<Integer> issue(final List<Integer> lottoNumbers) {
        List<Integer> generatedNumber = new ArrayList<>();
        for (int i = 0; i < LottoConstants.NUMBER_COUNT; i++) {
            generatedNumber.add(lottoNumbers.get(i));
        }
        return generatedNumber;
    }
}
