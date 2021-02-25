package lottogame.domain.lotto;

import java.util.Objects;
import java.util.regex.Pattern;

public class LottoNumber implements Comparable<LottoNumber> {
    private static final Pattern BONUS_NUMBER_PATTERN = Pattern.compile("^[0-9]*$");
    public final int number;

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber of(int number) {
        validateInt(number);
        return new LottoNumber(number);
    }

    public static LottoNumber of(String number) {
        validateString(number);
        return LottoNumber.of(Integer.parseInt(number));
    }

    private static void validateString(String number) {
        if (!BONUS_NUMBER_PATTERN.matcher(number).matches()) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateInt(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("로또 번호는 1과 45사이의 숫자여야 합니다.");
        }
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

    @Override
    public int compareTo(LottoNumber lottoNumber) {
        return this.number - lottoNumber.number;
    }
}
