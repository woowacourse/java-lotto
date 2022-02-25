package domain.Lotto;

import utils.ExceptionMessage;

<<<<<<< HEAD
import java.util.ArrayList;
=======
>>>>>>> d5f0ef8 (refactor: 패키지 분리)
import java.util.List;
import java.util.Objects;

public class LottoNumber {

    private static final int MINIMUM_LOTTO_NUMBER = 1;
    private static final int MAXIMUM_LOTTO_NUMBER = 45;
<<<<<<< HEAD
    private static final List<LottoNumber> CACHE = new ArrayList<>();

    static {
        for (int i = MINIMUM_LOTTO_NUMBER; i <= MAXIMUM_LOTTO_NUMBER; i++) {
            CACHE.add(new LottoNumber(i));
        }
    }
=======
>>>>>>> d5f0ef8 (refactor: 패키지 분리)

    private final int number;

    public LottoNumber(int number) {
        validateLottoNumberBound(number);
        this.number = number;
    }

<<<<<<< HEAD
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

    private void validateLottoNumberBound(int number) {
        if (number < MINIMUM_LOTTO_NUMBER || number > MAXIMUM_LOTTO_NUMBER) {
            throw new IllegalArgumentException(ExceptionMessage.LOTTO_NUMBER_OUT_OF_BOUND);
        }
    }

    public int getNumber() {
        return number;
=======
    private void validateLottoNumberBound(int number) {
        if (number < MINIMUM_LOTTO_NUMBER || number > MAXIMUM_LOTTO_NUMBER) {
            throw new IllegalArgumentException(ExceptionMessage.LOTTO_NUMBER_OUT_OF_BOUND);
        }
    }

    public int checkHit(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.contains(this)) {
            return 1;
        }
        return 0;
    }

    public int getNumber() {
        return this.number;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
>>>>>>> d5f0ef8 (refactor: 패키지 분리)
    }
}