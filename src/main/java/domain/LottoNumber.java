package domain;

import java.util.Objects;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {
    public static final int MINIMUM_LOTTO_NUMBER = 1;
    public static final int MAXIMUM_LOTTO_NUMBER = 45;
    private static final String REQUEST_1_TO_45_NUMBER = String.format("%d부터 %d 사이의 수를 입력해주세요.",
            MINIMUM_LOTTO_NUMBER, MAXIMUM_LOTTO_NUMBER);
    public static final LottoNumber[] LOTTO_NUMBER_CACHE = new LottoNumber[MAXIMUM_LOTTO_NUMBER + 1];

    private final int number;

    static {
        IntStream.rangeClosed(MINIMUM_LOTTO_NUMBER, MAXIMUM_LOTTO_NUMBER)
                .forEach(number -> LOTTO_NUMBER_CACHE[number] = new LottoNumber(number));
    }

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber valueOf(int number) {
        checkLottoNumberRange(number);
        return LOTTO_NUMBER_CACHE[number];
    }

    private static void checkLottoNumberRange(int number) {
        if (number < MINIMUM_LOTTO_NUMBER || number > MAXIMUM_LOTTO_NUMBER) {
            throw new IllegalArgumentException(REQUEST_1_TO_45_NUMBER);
        }
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

    @Override
    public int compareTo(LottoNumber o) {
        return number - o.number;
    }

    public int getNumber() {
        return number;
    }
}
