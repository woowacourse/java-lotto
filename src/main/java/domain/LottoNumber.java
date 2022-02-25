package domain;

import static utils.Messages.LOTTO_NUMBER_RANGE_ERROR_MESSAGE;

import java.util.ArrayList;
import java.util.List;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final int LOTTO_NUMBER_LOWER_BOUND = 1;
    private static final int LOTTO_NUMBER_UPPER_BOUND = 45;
    private static final List<LottoNumber> CACHE = new ArrayList<>();
    public static final int INDEX_MINUS_NUMBER = 1;

    private final int number;

    static {
        for (int i = LOTTO_NUMBER_LOWER_BOUND; i <= LOTTO_NUMBER_UPPER_BOUND; i++) {
            CACHE.add(new LottoNumber(i));
        }
    }

    private LottoNumber(final int number) {
        this.number = number;
    }

    public static LottoNumber valueOf(final int number) {
        validate(number);
        int index = number - INDEX_MINUS_NUMBER;
        return CACHE.get(index);
    }

    public static List<LottoNumber> values() {
        return CACHE;
    }

    public int get() {
        return number;
    }

    private static void validate(int number) {
        if (number < LOTTO_NUMBER_LOWER_BOUND || number > LOTTO_NUMBER_UPPER_BOUND) {
            throw new IllegalArgumentException(LOTTO_NUMBER_RANGE_ERROR_MESSAGE);
        }
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(this.number, o.number);
    }
}