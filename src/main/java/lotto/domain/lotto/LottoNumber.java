package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.List;


public class LottoNumber {

    private static final String ERROR_INTEGER_RANGE = "[ERROR] 1~45 사이의 수가 아닙니다.";
    private static final int LOTTO_MIN_RANGE = 1;
    private static final int LOTTO_MAX_RANGE = 45;

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

    public void checkRangeNumber(final int number) {
        if (!(number >= LOTTO_MIN_RANGE && number <= LOTTO_MAX_RANGE)) {
            throw new IllegalArgumentException(ERROR_INTEGER_RANGE);
        }
    }

    public static List<Integer> getRangeLottoNumbers() {
        List<Integer> numbers = new ArrayList<>();

        for (int i = LOTTO_MIN_RANGE; i <= LOTTO_MAX_RANGE; i++) {
            numbers.add(i);
        }

        return numbers;
    }

    public int getLottoNumber() {
        return lottoNumber;
    }
}
