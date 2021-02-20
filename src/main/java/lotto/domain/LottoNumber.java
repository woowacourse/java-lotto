package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static lotto.domain.LottoNumberGenerator.MAXIMUM_CANDIDATE_NUMBER;
import static lotto.domain.LottoNumberGenerator.MINIMUM_CANDIDATE_NUMBER;

public class LottoNumber {
    public static final String NON_NUMERIC_ERROR = "숫자만 입력 가능합니다.";
    public static final String NUMBER_OUT_OF_BOUNDS_ERROR = "1 에서 45 사이의 숫자만 입력해주세요.";

    private final int number;

    public LottoNumber(int number) {
        validateRangeOfNumber(number);
        this.number = number;
    }

    public LottoNumber(String number) {
        validateNumeric(number);
        validateRangeOfNumber(Integer.parseInt(number));
        this.number = Integer.parseInt(number);
    }

    public static List<LottoNumber> convertStringsToLottoNumbers(String[] numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (String number : numbers) {
            lottoNumbers.add(new LottoNumber(number));
        }
        return lottoNumbers;
    }

    private void validateNumeric(String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NON_NUMERIC_ERROR);
        }
    }

    private void validateRangeOfNumber(int number) {
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
