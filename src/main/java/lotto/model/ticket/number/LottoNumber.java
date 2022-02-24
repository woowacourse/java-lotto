package lotto.model.ticket.number;

import java.util.Objects;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {

    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;
    public static final LottoNumber[] LOTTO_NUMBER_CACHE = new LottoNumber[MAX_LOTTO_NUMBER + 1];

    static {
        IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                .forEach(number -> LOTTO_NUMBER_CACHE[number] = new LottoNumber(number));
    }

    private final int number;

    public static LottoNumber from(int number) {
        validate(number);
        return LOTTO_NUMBER_CACHE[number];
    }

    public LottoNumber(int number) {
        validate(number);
        this.number = number;
    }

    private static void validate(int number) {
        if (!isInLottoRange(number)) {
            throw new IllegalArgumentException(String.format("로또 숫자는 %d~%d 사이의 숫자여야 합니다.", MIN_LOTTO_NUMBER,
                    MAX_LOTTO_NUMBER));
        }
    }

    private static boolean isInLottoRange(int number) {
        return number <= MAX_LOTTO_NUMBER && number >= MIN_LOTTO_NUMBER;
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(this.getNumber(), o.getNumber());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "" + number;
    }
}
