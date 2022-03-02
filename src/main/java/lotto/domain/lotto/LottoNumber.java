package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.List;
import lotto.exception.InvalidException;

public class LottoNumber {

    private static final int LOTTO_MIN_RANGE = 1;
    private static final int LOTTO_MAX_RANGE = 45;

    private final int lottoNumber;

    public LottoNumber(String lottoNumber) {
        checkNumber(lottoNumber);
        this.lottoNumber = Integer.parseInt(lottoNumber);
    }

    private void checkNumber(String lottoNumber) {
        checkCanInteger(lottoNumber);
        checkRangeNumber(Integer.parseInt(lottoNumber));
    }

    private void checkCanInteger(String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(InvalidException.ERROR_INTEGER_RANGE);
        }
    }

    public void checkRangeNumber(int number) {
        if (!(number >= LOTTO_MIN_RANGE && number <= LOTTO_MAX_RANGE)) {
            throw new IllegalArgumentException(InvalidException.ERROR_INTEGER_RANGE);
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
