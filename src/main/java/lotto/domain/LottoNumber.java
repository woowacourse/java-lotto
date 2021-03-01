package lotto.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final int MIN_NUMBER_OF_LOTTO = 1;
    private static final int MAX_NUMBER_OF_LOTTO = 45;

    public static final Map<Integer, LottoNumber> lottoNumbers = new HashMap<>();

    static {
        for (int i = MIN_NUMBER_OF_LOTTO; i <= MAX_NUMBER_OF_LOTTO; i++) {
            lottoNumbers.put(i, new LottoNumber(i));
        }
    }

    private final int number;

    public LottoNumber(String number) {
        this(changeToInt(number));
    }

    public LottoNumber(int number) {
        validateNumberBound(number);
        this.number = number;
    }

    private static int changeToInt(String number) {
        number = number.trim();
        validateNumber(number);
        return Integer.parseInt(number);
    }

    private static void validateNumber(String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력 가능합니다.");
        }
    }

    private void validateNumberBound(int number) {
        if (number < MIN_NUMBER_OF_LOTTO || number > MAX_NUMBER_OF_LOTTO) {
            throw new IllegalArgumentException("로또 번호는 1~45 사이의 정수만 입력 가능합니다.");
        }
    }

    public int number() {
        return number;
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(number, o.number());
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
    public String toString() {
        return Integer.toString(number);
    }
}
