package lotto.domain;

import lotto.util.NumberGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static lotto.constant.ErrorMessage.DUPLICATE_LOTTO_NUMBER;
import static lotto.constant.ErrorMessage.INVALID_LOTTO_NUMBER_COUNT;

public class Lotto {
    public static final int PRICE = 1000;
    public static final int LOTTO_NUMBER_COUNT = 6;

    private final List<LottoNumber> numbers;

    public Lotto(NumberGenerator generator) {
        Set<LottoNumber> set = new TreeSet<>();
        while (set.size() < LOTTO_NUMBER_COUNT) {
            set.add(LottoNumber.random(generator));
        }
        this.numbers = new ArrayList<>(set);
    }

    public Lotto(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_COUNT.getMessage());
        }

        if (numbers.stream().distinct().count() != numbers.size()) {
            throw new IllegalArgumentException(DUPLICATE_LOTTO_NUMBER.getMessage());
        }

        this.numbers = numbers.stream()
            .map(LottoNumber::new)
            .toList();
    }

    public int findMatchCount(Lotto lotto) {
        int count = 0;
        for (int i = 0; i < numbers.size(); i++) {
            if (lotto.numbers.contains(numbers.get(i))) {
                count++;
            }
        }
        return count;
    }

    public boolean containsNumber(LottoNumber number) {
        return numbers.contains(number);
    }

    public List<LottoNumber> getNumbers() {
        return numbers;
    }
}
