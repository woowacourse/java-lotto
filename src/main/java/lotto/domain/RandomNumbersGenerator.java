package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class RandomNumbersGenerator implements NumbersGenerator{
    private final int minRange;
    private final int maxRange;
    private final int size;

    public RandomNumbersGenerator(int minRange, int maxRange, int size) {
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.size = size;
    }

    @Override
    public List<Integer> generate() {
        List<Integer> list = new ArrayList<>();

        while (list.size() < size) {
            int randomInt = (int)(Math.random() * maxRange) + minRange;
            if (!list.contains(randomInt)) {
                list.add(randomInt);
            }
        }

        return list;
    }

}
