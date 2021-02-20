package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LottoNumber {
    public static final int MINIMUM_CANDIDATE_NUMBER = 1;
    public static final int MAXIMUM_CANDIDATE_NUMBER = 45;
    public static final String NUMBER_OUT_OF_BOUNDS_ERROR = String.format(
            "%d 에서 %d 사이의 숫자만 입력해주세요.",
            MINIMUM_CANDIDATE_NUMBER,
            MAXIMUM_CANDIDATE_NUMBER
    );

    private final int number;

    public LottoNumber(int number) {
        validateRangeOfNumber(number);
        this.number = number;
    }

    public static List<LottoNumber> createLottoNumbers(int[] numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int number : numbers) {
            lottoNumbers.add(new LottoNumber(number));
        }
        return lottoNumbers;
    }

    public static void validateRangeOfNumber(int number) {
        if (number < MINIMUM_CANDIDATE_NUMBER || MAXIMUM_CANDIDATE_NUMBER < number) {
            throw new IllegalArgumentException(NUMBER_OUT_OF_BOUNDS_ERROR);
        }
    }

    public boolean hasAnyMatchingNumber(List<LottoNumber> numbers) {
        return numbers.contains(new LottoNumber(number));
    }

    public int getNumber() {
        return number;
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
    }
}
