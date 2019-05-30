package lotto.domain;

import lotto.domain.domainexception.InvalidLottoNumberException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lotto.domain.domainconstants.DomainConstants.*;

public class LottoNumber implements Comparable<LottoNumber> {
    private static final Map<Integer, LottoNumber> LOTTO_NUMBERS;
    private static final List<LottoNumber> LOTTO_NUMBERS_VALUES;

    private final int number;

    static {
        LOTTO_NUMBERS = new HashMap<>();
        IntStream.rangeClosed(MINIMUM_LOTTO_NUMBER, MAXIMUM_LOTTO_NUMBER)
                .forEachOrdered(value -> LOTTO_NUMBERS.put(value, new LottoNumber(value)));

        LOTTO_NUMBERS_VALUES = new ArrayList<>(LOTTO_NUMBERS.values());
    }

    private LottoNumber(int number) {
        validLottoNumber(number);
        this.number = number;
    }

    private static void validLottoNumber(int number) {
        if (MINIMUM_LOTTO_NUMBER > number || number > MAXIMUM_LOTTO_NUMBER) {
            throw new InvalidLottoNumberException("로또 넘버는 " + MINIMUM_LOTTO_NUMBER + "~" + MAXIMUM_LOTTO_NUMBER + "사이의 값을 가집니다.");
        }
    }

    public static LottoNumber generateNumber(int lottoNumber) {
        validLottoNumber(lottoNumber);
        return LOTTO_NUMBERS.get(lottoNumber);
    }

    static Set<LottoNumber> generateRandomLottoNumbers() {
        Collections.shuffle(LOTTO_NUMBERS_VALUES);
        return LOTTO_NUMBERS_VALUES.stream()
                .limit(LOTTO_SIZE)
                .collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(this.number, o.number);
    }
}