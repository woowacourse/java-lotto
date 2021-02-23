package lotto.domain;

import lotto.exception.IllegalLottoNumberException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^[0-9]*$");
    private static final int MIN_LOTTO_NUMBER = 1;

    private static final Map<Integer, LottoNumber> lottoNumbers = new HashMap<>();

    static {
        IntStream.range(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                .forEach(number -> lottoNumbers.put(number, new LottoNumber(number)));
    }

    private final int value;

    private LottoNumber(int input) {
        this.value = input;
    }

    public static LottoNumber of(String input) {
        validateLottoNumber(input);
        return lottoNumbers.get(Integer.parseInt(input));
    }

    private static void validateLottoNumber(String input) {
        validateNumberByFormat(input);
        validateLottoNumberByRange(input);
    }

    private static void validateNumberByFormat(String input) {
        if (!NUMBER_PATTERN.matcher(input).matches()) {
            throw new IllegalLottoNumberException(input + " : 올바른 형식이 아닙니다.");
        }
    }

    private static void validateLottoNumberByRange(String input) {
        int value = Integer.parseInt(input);
        if (value > MAX_LOTTO_NUMBER || value < MIN_LOTTO_NUMBER) {
            throw new IllegalLottoNumberException(input + " : 로또번호의 범위를 벗어납니다.");
        }
    }

    private int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public int compareTo(LottoNumber anotherLottoNumber) {
        return Integer.compare(this.getValue(), anotherLottoNumber.getValue());
    }
}
