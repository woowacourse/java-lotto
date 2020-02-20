package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class LottoNumbers {

    private static final int LAST_LOTTO_NUMBER = 45;
    private static final int FIRST_LOTTO_NUMBER = 1;

    private static List<Integer> lottoNumbers = new ArrayList<>();

    public LottoNumbers() {
        for (int i = FIRST_LOTTO_NUMBER; i <= LAST_LOTTO_NUMBER; i++) {
            lottoNumbers.add(i);
        }
    }

    public static List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }
}
