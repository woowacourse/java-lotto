package lotto.domain;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final int MIN_NUMBER_OF_LOTTO = 1;
    private static final int MAX_NUMBER_OF_LOTTO = 45;
    private final int number;

    public LottoNumber(Integer number) {
        this(Integer.toString(number));
    }

    public LottoNumber(String number) {
        number = number.trim();
        validateLottoNumber(number);
        this.number = Integer.parseInt(number);
    }

    public int getNumber() {
        return number;
    }

    public String getStringNumber() {
        return String.valueOf(number);
    }

    private void validateLottoNumber(String number) {
        validateEmpty(number);
        validateNumber(number);
        validateNumberBound(number);
    }

    private void validateNumber(String number) {
        try {
            Integer.parseInt(number.trim());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("숫자만 입력 가능합니다.");
        }
    }

    private void validateEmpty(String number) {
        if (number.isEmpty()) {
            throw new IllegalArgumentException("빈 값은 입력할 수 없습니다.");
        }
    }

    private void validateNumberBound(String number) {
        if (Integer.parseInt(number) < MIN_NUMBER_OF_LOTTO
                || Integer.parseInt(number) > MAX_NUMBER_OF_LOTTO) {
            throw new IllegalArgumentException("1~45 사이의 정수만 입력 가능합니다.");
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
