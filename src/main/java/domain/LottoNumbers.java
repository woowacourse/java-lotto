package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumbers {
    private static final int MAX_LOTTO_NUMBER = 45;

    private static List<LottoNumber> lottoNumbers;
    
    static {
        lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= MAX_LOTTO_NUMBER; i++) {
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
