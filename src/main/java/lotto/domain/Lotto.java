package lotto.domain;

import lotto.constant.ErrorMessage;

import java.util.*;

import static lotto.constant.ErrorMessage.*;

public class Lotto {
    public static final int PRICE = 1000;
    public static final int LOTTO_NUMBER_COUNT = 6;
    public static final int MINIMUM_NUMBER = 1;
    public static final int MAXIMUM_NUMBER = 45;

    public final List<Integer> numbers;

    public Lotto() {
        Set<Integer> set = new TreeSet();
        while (set.size() < LOTTO_NUMBER_COUNT) {
            set.add((int)(Math.random() * MAXIMUM_NUMBER) + MINIMUM_NUMBER);
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

        numbers.forEach(number ->{
            if (number < MINIMUM_NUMBER || number > MAXIMUM_NUMBER) {
                throw new IllegalArgumentException(OUT_OF_RANGE_LOTTO_NUMBER.getMessage());
            }
        });
        this.numbers = numbers;
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

    public boolean isMatchBonus(int bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public boolean containsNumber(int bonusNumber) {
        return numbers.contains(bonusNumber);
    }
}
