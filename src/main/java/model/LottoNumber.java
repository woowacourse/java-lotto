package model;

import exception.InvalidRangeLottoNumberException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber {
    private static final int INDEX_OFFSET = 1;
    private static final int MINIMUM_LOTTO_NUMBER = 1;
    private static final int MAXIMUM_LOTTO_NUMBER = 45;
    private static final List<LottoNumber> LOTTO_NUMBERS;

    private final int lottoNumber;

    static {
        LOTTO_NUMBERS = IntStream.rangeClosed(MINIMUM_LOTTO_NUMBER, MAXIMUM_LOTTO_NUMBER)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
    }

    private LottoNumber(int number) {
        this.lottoNumber = number;
    }

    public static LottoNumber of(int number) {
        if (isInvalidRange(number)) {
            throw new InvalidRangeLottoNumberException();
        }
        return LOTTO_NUMBERS.get(number - INDEX_OFFSET);
    }

    private static boolean isInvalidRange(int number) {
        return MINIMUM_LOTTO_NUMBER > number || number > MAXIMUM_LOTTO_NUMBER;
    }

    public static LottoNumber parse(String text) {
        return new LottoNumber(Integer.parseInt(text));
    }

    public int intValue() {
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
