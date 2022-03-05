package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoNumber {

    private static final int MIN = 1;
    private static final int MAX = 45;

    private static final String ERROR_INTEGER_RANGE = String.format("[ERROR] %d~%d 사이의 수가 아닙니다.", MIN, MAX);

    private static final List<Integer> candidateLottoNumbers = new ArrayList<>();

    static {
        for (int i = MIN; i <= MAX; i++) {
            candidateLottoNumbers.add(i);
        }
    }

    private final int lottoNumber;

    public LottoNumber(final String lottoNumber) {
        checkNumber(lottoNumber);
        this.lottoNumber = Integer.parseInt(lottoNumber);
    }

    public LottoNumber(final int lottoNumber) {
        checkRangeNumber(lottoNumber);
        this.lottoNumber = lottoNumber;
    }

    public static List<Integer> getcandidateLottoNumbers() {
        return candidateLottoNumbers;
    }

    private void checkNumber(final String lottoNumber) {
        checkCanInteger(lottoNumber);
        checkRangeNumber(Integer.parseInt(lottoNumber));
    }

    private void checkCanInteger(final String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ERROR_INTEGER_RANGE);
        }
    }

    public static void checkRangeNumber(final int number) {
        if (!(number >= MIN && number <= MAX)) {
            throw new IllegalArgumentException(ERROR_INTEGER_RANGE);
        }
    }

    public static LottoNumber of(int number) {
        checkRangeNumber(number);
        return new LottoNumber(number);
    }

    public boolean equals(int number) {
        return this.lottoNumber == number;
    }

    public int getLottoNumber() {
        return lottoNumber;
    }
}
