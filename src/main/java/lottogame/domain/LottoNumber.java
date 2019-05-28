package lottogame.domain;

import lottogame.utils.InvalidLottoNumberException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LottoNumber {
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int DUMMY_LOTTO_INDEX = 1;

    private static final List<LottoNumber> lottoNumbers = new ArrayList<>();

    private int lottoNumber;

    static {
        for (int lottoNumber = MIN_LOTTO_NUMBER; lottoNumber <= MAX_LOTTO_NUMBER; lottoNumber++) {
            lottoNumbers.add(new LottoNumber(lottoNumber));
        }
    }

    private LottoNumber(int lottoNumber) {
        this.lottoNumber = lottoNumber;
    }

    public static LottoNumber getLottoNumber(int lottoNumber) {
        validateLottoNumber(lottoNumber);
        return lottoNumbers.get(lottoNumber - DUMMY_LOTTO_INDEX);
    }

    private static void validateLottoNumber(int lottoNumber) {
        if (lottoNumber < MIN_LOTTO_NUMBER || lottoNumber > MAX_LOTTO_NUMBER) {
            throw new InvalidLottoNumberException("올바른 로또 번호가 아닙니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return lottoNumber == that.lottoNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumber);
    }
}
