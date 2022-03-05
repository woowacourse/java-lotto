package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class LottoNumber {

    private static final int MIN = 1;
    private static final int MAX = 45;
    private static final String ERROR_INTEGER_RANGE = String.format("[ERROR] %d~%d 사이의 수가 아닙니다.", MIN, MAX);

    private static final List<Integer> candidateLottoNumbers = new ArrayList<>();

    static {
        IntStream.range(MIN, MAX + 1)
                .forEach(i -> candidateLottoNumbers.add(i));
    }

    private final int lottoNumber;

    public LottoNumber(final String lottoNumber) {
        checkNumber(lottoNumber);
        this.lottoNumber = Integer.parseInt(lottoNumber);
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
        return new LottoNumber(Integer.toString(number));
    }

    public static List<Integer> getcandidateLottoNumbers() {
        return candidateLottoNumbers;
    }

    public boolean equals(int number) {
        return this.lottoNumber == number;
    }

    public int getLottoNumber() {
        return lottoNumber;
    }
}
