package lotto.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final int MIN_NUMBER_OF_LOTTO = 1;
    private static final int MAX_NUMBER_OF_LOTTO = 45;
    private static final Map<Integer, LottoNumber> CACHE = new HashMap<>();

    private final int number;

    static {
        for (int i = MIN_NUMBER_OF_LOTTO; i <= MAX_NUMBER_OF_LOTTO; i++) {
            CACHE.put(i, new LottoNumber(i));
        }
    }

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber from(int number) {
        if (CACHE.get(number) == null) {
            throw new IllegalArgumentException("1~45 사이의 정수만 입력 가능합니다.");
        }
        return CACHE.get(number);
    }

    public static LottoNumber from(String number) {
        number = number.trim();
        return LottoNumber.from(Integer.parseInt(number));
    }

    public int getNumber() {
        return number;
    }

    public String getStringNumber() {
        return String.valueOf(number);
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
