package lotto.domain;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;
    private static final String EMPTY_INPUT_MSG = "로또 번호가 입력되지 않았습니다.";
    private static final String NOT_IN_SCOPE_NUMBERS_MSG = "로또 번호의 범위가 잘못되었습니다.";
    private static final String NOT_NUMBER_MSG = "정수로 입력하셔야 합니다.";

    private int number;

    public LottoNumber(String number) {
        this(validateNumber(number));
    }

    public LottoNumber(int number) {
        validateNumberScope(number);
        this.number = number;
    }

    private static void validateEmpty(String lottoNumber) {
        if (lottoNumber == null || lottoNumber.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_INPUT_MSG);
        }
    }

    private static int validateNumber(String lottoNumber) {
        validateEmpty(lottoNumber);
        try {
            return Integer.parseInt(lottoNumber.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_NUMBER_MSG);
        }
    }

    private void validateNumberScope(int lottoNumber) {
        if (lottoNumber < MIN_LOTTO_NUMBER
                || lottoNumber > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(NOT_IN_SCOPE_NUMBERS_MSG);
        }
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return Integer.toString(number);
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

    @Override
    public int compareTo(LottoNumber compareLottoNumber) {
        return this.number - compareLottoNumber.number;
    }
}

