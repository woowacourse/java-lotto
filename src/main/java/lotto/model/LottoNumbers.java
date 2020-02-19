package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class LottoNumbers {
    private static List<Integer> lottoNumbers = new ArrayList<>();

    public static void lottoNumbersCreate() {
        new LottoNumbers();
    }

    private LottoNumbers() {
        for (int i = 1; i <= 45; i++) {
            lottoNumbers.add(i);
        }
    }

    public static List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }
}
