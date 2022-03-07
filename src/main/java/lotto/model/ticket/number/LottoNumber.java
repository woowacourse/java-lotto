package lotto.model.ticket.number;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {

    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;
    private static final List<LottoNumber> LOTTO_NUMBER_CACHE;
    private static final String LOTTO_NUMBER_IN_RANGE_MESSAGE = "로또 숫자는 %d~%d 사이의 숫자여야 합니다.";

    static {
        LOTTO_NUMBER_CACHE = IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                .mapToObj(LottoNumber::new).collect(Collectors.toUnmodifiableList());
    }

    private final int number;

    public static LottoNumber from(int number) {
        validate(number);
        return LOTTO_NUMBER_CACHE.get(number - 1);
    }

    private LottoNumber(int number) {
        validate(number);
        this.number = number;
    }

    private static void validate(int number) {
        if (!isInLottoRange(number)) {
            throw new IllegalArgumentException(String.format(LOTTO_NUMBER_IN_RANGE_MESSAGE, MIN_LOTTO_NUMBER,
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
        return getNumber() == that.getNumber();
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
        return String.valueOf(number);
    }
}
