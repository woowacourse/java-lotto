package lotto.domain;

import lotto.util.NumberGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.IntStream;

import static lotto.constant.ErrorMessage.DUPLICATE_LOTTO_NUMBER;
import static lotto.constant.ErrorMessage.INVALID_LOTTO_NUMBER_COUNT;

public class Lotto {
    public static final int PRICE = 1000;
    public static final int LOTTO_NUMBER_COUNT = 6;

    private final List<LottoNumber> numbers;

    public Lotto(NumberGenerator generator) {
        Set<LottoNumber> set = new TreeSet<>();
        while (set.size() < LOTTO_NUMBER_COUNT) {
            set.add(LottoNumber.generate(generator));
        }
        numbers = new ArrayList<>(set);
    }

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers.stream()
            .map(LottoNumber::new)
            .toList();
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_COUNT.getMessage());
        }

        if (numbers.stream().distinct().count() != numbers.size()) {
            throw new IllegalArgumentException(DUPLICATE_LOTTO_NUMBER.getMessage());
        }
    }

    public int findMatchCount(Lotto lotto) {
        return (int) IntStream.range(0, numbers.size())
            .filter(i -> lotto.numbers.contains(numbers.get(i)))
            .count();
    }

    public boolean containsNumber(LottoNumber number) {
        return numbers.contains(number);
    }

    public List<LottoNumber> getNumbers() {
        return numbers;
    }
}
