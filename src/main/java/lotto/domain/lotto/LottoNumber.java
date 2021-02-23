package lotto.domain.lotto;

import java.util.Objects;
import java.util.regex.Pattern;

public class LottoNumber implements Comparable<LottoNumber> {
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^[0-9]*$");

    private final int value;

    public LottoNumber(String number) {
        validateLottoNumber(number);
        this.value = Integer.parseInt(number);
    }

    private void validateLottoNumber(String number) {
        checkLottoNumber(number);
        checkLottoNumberRange(number);
    }

    private void checkLottoNumber(String number) {
        if ("".equals(number) || !NUMBER_PATTERN.matcher(number).matches()) {
            throw new IllegalArgumentException("[ERROR] 로또 번호가 정수가 아닙니다.");
        }
    }

    private void checkLottoNumberRange(String number) {
        if (Integer.parseInt(number) < MIN_LOTTO_NUMBER ||
                Integer.parseInt(number) > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 로또 번호 범위를 초과했습니다.");
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
