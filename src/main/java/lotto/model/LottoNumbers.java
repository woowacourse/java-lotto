package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class LottoNumbers {
    public static final int LAST_LOTTO_NUMBER = 45;
    public static final int FIRST_LOTTO_NUMBER = 1;
    private static List<Integer> lottoNumbers = new ArrayList<>();

    public static void lottoNumbersCreate() {
        new LottoNumbers();
    }

    private LottoNumbers() {
        for (int i = FIRST_LOTTO_NUMBER; i <= LAST_LOTTO_NUMBER; i++) {
            lottoNumbers.add(i);
        }
    }

    public static List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }
}
