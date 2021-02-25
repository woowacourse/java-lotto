package lotto.domain;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final int MIN_NUMBER_OF_LOTTO = 1;
    private static final int MAX_NUMBER_OF_LOTTO = 45;
    private static final LottoNumber[] CACHE = new LottoNumber[MAX_NUMBER_OF_LOTTO];

    private final int number;

    static {
        for (int i = 0; i < CACHE.length; i++) {
            CACHE[i] = new LottoNumber(i + MIN_NUMBER_OF_LOTTO);
        }
    }

    private LottoNumber(int number) {
        validateNumberBound(number);
        this.number = number;
    }

    public static LottoNumber from(int number) {
        if (MIN_NUMBER_OF_LOTTO <= number && number <= MAX_NUMBER_OF_LOTTO) {
            return CACHE[number - 1];
        }
        return new LottoNumber(number);
    }

    public static LottoNumber from(String number) {
        number = number.trim();
        validateLottoNumber(number);
        return LottoNumber.from(Integer.parseInt(number));
    }

    private void validateNumberBound(int number) {
        if (number < MIN_NUMBER_OF_LOTTO || number > MAX_NUMBER_OF_LOTTO) {
            throw new IllegalArgumentException("1~45 사이의 정수만 입력 가능합니다.");
        }
    }

    public int getNumber() {
        return number;
    }

    public String getStringNumber() {
        return String.valueOf(number);
    }

    private static void validateLottoNumber(String number) {
        validateEmpty(number);
        validateNumber(number);
    }

    private static void validateEmpty(String number) {
        if (number.isEmpty()) {
            throw new IllegalArgumentException("빈 값은 입력할 수 없습니다.");
        }
    }

    private static void validateNumber(String number) {
        try {
            Integer.parseInt(number.trim());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("숫자만 입력 가능합니다.");
        }
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(number, o.getNumber());
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
