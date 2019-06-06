package lotto.domain;

import lotto.exception.UnexpectedInputRangeException;

import java.util.*;

public class LottoNumber implements Comparable<LottoNumber> {
    private static final int FIRST_BOUND_OF_LOTTO_NUMBER = 1;
    private static final int LAST_BOUND_OF_LOTTO_NUMBER = 45;
    private static final int DEFAULT_INDEX_OF_LOTTO_NUMBER = 0;
    private static final int MAX_INDEX_OF_LOTTO_NUMBER = 6;
    private static final Map<Integer, LottoNumber> numbers = new HashMap<>();

    private final int number;

    static {
        for (int i = FIRST_BOUND_OF_LOTTO_NUMBER; i <= LAST_BOUND_OF_LOTTO_NUMBER; i++) {
            numbers.put(i, new LottoNumber(i));
        }
    }

    public LottoNumber(final int number) {
        this.number = number;
    }

    public static LottoNumber getNumber(int key) throws UnexpectedInputRangeException {
        validateNumberBound(key);

        return numbers.get(key);
    }

    public static List<LottoNumber> getRandomNumbers() {
        List<LottoNumber> randomNumbers = new ArrayList<>(numbers.values());
        Collections.shuffle(randomNumbers);

        return randomNumbers.subList(DEFAULT_INDEX_OF_LOTTO_NUMBER, MAX_INDEX_OF_LOTTO_NUMBER);
    }

    private static void validateNumberBound(int key) {
        if (key < FIRST_BOUND_OF_LOTTO_NUMBER || key > LAST_BOUND_OF_LOTTO_NUMBER) {
            throw new UnexpectedInputRangeException("로또 번호의 범위에 벗어납니다.");
        }
    }

    @Override
    public String toString() {
        return number + "";
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(this.number, o.number);
    }
}
