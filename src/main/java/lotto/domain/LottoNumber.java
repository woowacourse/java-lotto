package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumber {

    public static final int LOTTO_SELECT_NUMBER = 6;
    private static final int LOTTO_MIN_RANGE = 1;
    private static final int LOTTO_MAX_RANGE = 45;

    private LottoNumber() {

    }

    public static List<Integer> createLottoNumbers() {
        List<Integer> lottoNumbers = new ArrayList<>();
        setRangeLottoNumber(lottoNumbers);

        Collections.shuffle(lottoNumbers);
        return lottoNumbers.subList(0, LOTTO_SELECT_NUMBER);
    }

    private static List<Integer> setRangeLottoNumber(List<Integer> values) {
        for (int i = LOTTO_MIN_RANGE; i <= LOTTO_MAX_RANGE; i++) {
            values.add(i);
        }
        return values;
    }
}
