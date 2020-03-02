package domain;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.*;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {
    private static final int MIN_RANGE = 1;
    private static final int MAX_RANGE = 45;
    private static final String MAKE_STR = "";

    private static final Map<Integer, LottoNumber> lottoNumberPool = new HashMap<>();

    static {
        IntStream.rangeClosed(MIN_RANGE, MAX_RANGE)
                .forEach(number -> lottoNumberPool.put(number, createLottoNumber(number)));
    }

    private int lottoNumber;

    private LottoNumber(int input) {
        this.lottoNumber = input;
    }

    private static LottoNumber createLottoNumber(int number) {
        return new LottoNumber(number);
    }

    private static void validateNullOrBlank(String input) {
        if (StringUtils.isBlank(input)) {
            throw new IllegalArgumentException(String.format("input값이 공백 또는 null입니다. 현재 input값은 %s 입니다.", input));
        }
    }

    private static void validateNumeric(String input) {
        if (!NumberUtils.isDigits(input)) {
            throw new IllegalArgumentException(String.format("input값이 숫자가 아닙니다. 현재 input값은 %s 입니다.", input));
        }
    }

    private static void validateLottoNumberRange(int number) {
        if (number < MIN_RANGE || number > MAX_RANGE) {
            throw new IllegalArgumentException(String.format("범위를 벗어나는 로또 숫자입니다. 현재 숫자는 %d 입니다", number));
        }
    }


    @Override
    public int compareTo(LottoNumber number) {
        return this.lottoNumber - number.lottoNumber;
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

    @Override
    public String toString() {
        return lottoNumber + MAKE_STR;
    }

    public static LottoNumber from(String input) {
        validateNullOrBlank(input);
        validateNumeric(input);
        int number = Integer.parseInt(input);
        validateLottoNumberRange(number);
        return lottoNumberPool.get(number);
    }

    public static LottoNumber from(int number) {
        validateLottoNumberRange(number);
        return lottoNumberPool.get(number);
    }
}
