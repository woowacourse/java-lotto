package constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumbers {
    public static final List<Integer> LOTTO_NUMBERS;

    static {
        List<Integer> lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            lottoNumbers.add(i);
        }

        LOTTO_NUMBERS = Collections.unmodifiableList(lottoNumbers);
    }

    private LottoNumbers() {
    }
}
