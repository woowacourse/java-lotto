package lotto.utils;

import lotto.domain.LottoNumber;

import java.util.ArrayList;
import java.util.List;

public class LottoNumberFactory {
    private static final int LOTTO_NUMBER_LOWER_BOUND = 1;
    private static final int LOTTO_NUMBER_UPPER_BOUND = 45;

    public static List<LottoNumber> createLottoNumberList() {
        List<LottoNumber> lottoNumberList = new ArrayList<>();

        for (int i = LOTTO_NUMBER_LOWER_BOUND; i <= LOTTO_NUMBER_UPPER_BOUND; i++) {
            lottoNumberList.add(new LottoNumber(i));
        }
        return lottoNumberList;
    }
}
