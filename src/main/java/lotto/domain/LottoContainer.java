package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoContainer {
    private static final int LOTTO_LOWER_BOUND = 1;
    private static final int LOTTO_UPPER_BOUND = 45;

    private static final List<LottoNumber> lottoNumbers = new ArrayList<>();
    
    static {
        for (int i = LOTTO_LOWER_BOUND; i <= LOTTO_UPPER_BOUND; i++) {
            lottoNumbers.add(LottoNumber.from(i));
        }
    }

    public static List<LottoNumber> createLottoNumbers() {
        return new ArrayList<>(lottoNumbers);
    }
}
