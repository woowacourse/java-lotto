package domain;

import java.util.ArrayList;
import java.util.List;
import validator.LottoNumberValidator;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final int LOTTO_NUMBER_LOWER_BOUND = 1;
    private static final int LOTTO_NUMBER_UPPER_BOUND = 45;
    private static final List<LottoNumber> CACHE = new ArrayList<>();

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
        LottoNumberValidator.validate(number);
        return CACHE.get(number - 1);
    }

    public static List<LottoNumber> values() {
        return CACHE;
    }

    public int get() {
        return number;
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(this.number, o.number);
    }
}