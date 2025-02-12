package lotto.domain;

import java.util.*;

public class Lotto {
    public static final int PRICE = 1000;
    public List<Integer> numbers = new ArrayList<>();

    public Lotto() {
        Set<Integer> set = new TreeSet();
        while (set.size() < 6) {
            set.add((int)(Math.random() * 45) + 1);
        }
        numbers.addAll(set);
    }

    public Lotto(List<Integer> numbers) {
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
}
