package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.domain.LottoConstant;

public class LottoNumber {

    private static final int LOTTO_SIZE = 6;

    private LottoNumber() {
    }

    public static List<Integer> createLottoNumbers() {
        List<Integer> lottoNumbers = new ArrayList<>();
        LottoRange.setRangeLottoNumber(lottoNumbers);
        Collections.shuffle(lottoNumbers);

        return lottoNumbers.subList(0, LOTTO_SIZE);
    }
}
