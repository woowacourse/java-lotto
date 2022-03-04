package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LottoNumber {

    private static final int LOTTO_MIN_RANGE = 1;
    private static final int LOTTO_MAX_RANGE = 45;
    private static final String ERROR_INTEGER_RANGE = String.format("[ERROR] %d~%d 사이의 수가 아닙니다.", LOTTO_MIN_RANGE, LOTTO_MAX_RANGE);
    private static final List<Integer> candidateLottoNumbers = new ArrayList<>();
    static {
        for (int i=LottoNumber.LOTTO_MIN_RANGE; i<= LottoNumber.LOTTO_MAX_RANGE; i++){
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
        if (!(number >= LOTTO_MIN_RANGE && number <= LOTTO_MAX_RANGE)) {
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

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumber);
    }
}
