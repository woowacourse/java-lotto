package model.lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class LottoNumbers {
    private static final List<Integer> lottoNumbers = new ArrayList<>();
    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;

    static {
        IntStream.range(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER).forEach(lottoNumbers::add);
    }

    public static List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }
}
