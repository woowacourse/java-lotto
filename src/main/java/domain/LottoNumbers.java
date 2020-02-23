package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumbers {
    private static List<LottoNumber> lottoNumbers;

    static {
        lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
    }

    public static void shuffleLottoNumbers() {
        Collections.shuffle(lottoNumbers);
    }

    public static List<LottoNumber> getInstance() {
        return lottoNumbers;
    }
}
