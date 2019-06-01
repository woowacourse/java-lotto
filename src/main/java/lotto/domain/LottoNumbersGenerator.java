package lotto.domain;

import java.util.*;

public class LottoNumbersGenerator {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int SUBLIST_LOWER_BOUND = 0;
    private static final int SUBLIST_UPPER_BOUND = 6;
    private static final List<LottoNumber> totalLottoNumbers = new ArrayList<>();

    static {
        for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            LottoNumber lottoNumber = LottoNumber.valueOf(i);
            totalLottoNumbers.add(lottoNumber);
        }
    }

    private LottoNumbersGenerator() {
        throw new AssertionError();
    }

    public static LottoNumber getLottoNumber(int number) {
        return LottoNumber.valueOf(number);
    }

    public static LottoNumbers getLottoNumbers() {
        Collections.shuffle(totalLottoNumbers, new Random());
        List<LottoNumber> lottoNumbers = new ArrayList<>(
                totalLottoNumbers.subList(SUBLIST_LOWER_BOUND, SUBLIST_UPPER_BOUND));
        return new LottoNumbers(lottoNumbers);
    }

    public static LottoNumbers getLottoNumbers(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int number : numbers) {
            lottoNumbers.add(getLottoNumber(number));
        }
        return new LottoNumbers(lottoNumbers);
    }
}
