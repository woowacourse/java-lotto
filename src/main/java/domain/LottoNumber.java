package domain;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {
    private static final int MIN_LOTTO_NUMBER_RANGE = 1;
    private static final int MAX_LOTTO_NUMBER_RANGE = 45;
    private static final String MAKE_STR = "";

    private static final Map<Integer, LottoNumber> lottoNumberPool = new HashMap<>();

    static {
        IntStream.rangeClosed(MIN_LOTTO_NUMBER_RANGE, MAX_LOTTO_NUMBER_RANGE)
                .forEach(number -> lottoNumberPool.put(number, new LottoNumber(number)));
    }

    private int lottoNumber;

    LottoNumber(int input) {
        validateLottoNumberRange(input);
        this.lottoNumber = input;
    }

    LottoNumber(String input) {
        validateNullOrBlank(input);
        int parseNumber = validateParseInteger(input);
        validateBonusNumberRange(parseNumber);
        this.lottoNumber = parseNumber;
    }

    private void validateBonusNumberRange(int parseNumber) {
        if (parseNumber < MIN_LOTTO_NUMBER_RANGE || parseNumber > MAX_LOTTO_NUMBER_RANGE) {
            throw new IllegalArgumentException("잘못된 범위의 숫자를 입력하였습니다.");
        }
    }

    private int validateParseInteger(String input) {
        int parseNumber;
        try {
            parseNumber = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 문자를 입력하였습니다.");
        }
        return parseNumber;
    }

    private void validateNullOrBlank(String input) {
        if (StringUtils.isBlank(input)) {
            throw new IllegalArgumentException("null 또는 빈 문자를 입력하였습니다.");
        }
    }

    private static void validateLottoNumberRange(int number) {
        if (number < MIN_LOTTO_NUMBER_RANGE || number > MAX_LOTTO_NUMBER_RANGE) {
            throw new IllegalArgumentException("범위를 벗어나는 로또 숫자입니다.");
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

    public static LottoNumber getLottoNumber(int number) {
        return lottoNumberPool.get(number);
    }
}
