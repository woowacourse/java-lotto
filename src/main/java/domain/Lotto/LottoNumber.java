package domain.Lotto;

import utils.ExceptionMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LottoNumber {

    private static final int MINIMUM_LOTTO_NUMBER = 1;
    private static final int MAXIMUM_LOTTO_NUMBER = 45;
    private static final List<LottoNumber> CACHE = new ArrayList<>();

    static {
        for (int i = MINIMUM_LOTTO_NUMBER; i <= MAXIMUM_LOTTO_NUMBER; i++) {
            CACHE.add(new LottoNumber(i));
        }
    }

    private final int number;

    public LottoNumber(int number) {
        validateLottoNumberBound(number);
        this.number = number;
    }

    private void validateLottoNumberBound(int number) {
        if (number < MINIMUM_LOTTO_NUMBER || number > MAXIMUM_LOTTO_NUMBER) {
            throw new IllegalArgumentException(ExceptionMessage.LOTTO_NUMBER_OUT_OF_BOUND);
        }
    }

    public static LottoNumber valueOf(int number) {
        LottoNumber lottoNumber = CACHE.get(number - 1);

        if (lottoNumber == null) {
            lottoNumber = new LottoNumber(number);
        }
        return lottoNumber;
    }

    public static List<LottoNumber> values() {
        return CACHE;
    }

    public int getNumber() {
        return number;
    }
}