package model;

import exception.InvalidRangeLottoNumberException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LottoNumber {
    private static final int MINIMUM_LOTTO_NUMBER = 1;
    private static final int MAXIMUM_LOTTO_NUMBER = 45;
    private final int lottoNumber;

    public LottoNumber(int number) {
        if (isInvalidRange(number)) {
            throw new InvalidRangeLottoNumberException();
        }
        this.lottoNumber = number;
    }

    public static LottoNumber parse(String text) {
        if (isNotNumeric(text)) {
            throw new IllegalArgumentException("로또번호는 반드시 숫자여야 합니다.");
        }
        return new LottoNumber(Integer.parseInt(text));
    }

    private static boolean isNotNumeric(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static List<LottoNumber> convertAll(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    private boolean isInvalidRange(int number) {
        return MINIMUM_LOTTO_NUMBER > number || number > MAXIMUM_LOTTO_NUMBER;
    }

    public int getIntValue() {
        return lottoNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumber that = (LottoNumber) o;
        return lottoNumber == that.lottoNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumber);
    }
}
