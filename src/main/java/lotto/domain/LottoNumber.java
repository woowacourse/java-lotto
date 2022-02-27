package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumber {

    private LottoNumber() {
    }

    public static List<Integer> createLottoNumbers() {
        List<Integer> lottoNumbers = new ArrayList<>();
        setRangeLottoNumber(lottoNumbers);
        Collections.shuffle(lottoNumbers);

        return lottoNumbers.subList(0, Constant.LOTTO_SIZE);
    }

    private static void setRangeLottoNumber(final List<Integer> values) {
        for (int i = Constant.LOTTO_MIN_RANGE; i <= Constant.LOTTO_MAX_RANGE; i++) {
            values.add(i);
        }
    }
}
