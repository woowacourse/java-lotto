package lotto.domain.ticket;

import lotto.domain.ticket.exception.InvalidNumberRangeException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumber implements Comparable<LottoNumber> {
    private static final int MAX_NUMBER = 45;
    private static final int MINIMUN_NUMBER = 1;
    private static final List<LottoNumber> LOTTO_NUMBER_POOL;
    private final int number;

    static {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int i = MINIMUN_NUMBER; i <= MAX_NUMBER; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
        LOTTO_NUMBER_POOL = Collections.unmodifiableList(lottoNumbers);
    }

    private LottoNumber(int number) {
        this.number = number;
    }
    public static LottoNumber of(int number) {
        validRange(number);
        return LOTTO_NUMBER_POOL.get(number - 1);
    }

    public static List<LottoNumber> getLottoNumberPool() {
        return LOTTO_NUMBER_POOL;
    }

    private static void validRange(final int number) {
        if (isInvalidRange(number)) {
            throw new InvalidNumberRangeException("로또 숫자는 1이상 45 이하여야 합니다.");
        }
    }

    private static boolean isInvalidRange(int number) {
        return number > MAX_NUMBER || number < MINIMUN_NUMBER;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public int compareTo(final LottoNumber o) {
        return number - o.number;
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }
}
