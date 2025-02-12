package lotto.utils;

import java.util.List;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

public class NumberGenerator {

    public static List<Integer> numberGenerator(int numberQuantity, int minValue, int maxValue) {
        Random random = new Random();

        SortedSet<Integer> lottoSet = new TreeSet<>();
        while (true) {
            int ranNum = random.nextInt(maxValue - minValue + 1) + minValue;
            lottoSet.add(ranNum);
            if (lottoSet.size() == numberQuantity) {
                break;
            }
        }

        return List.copyOf(lottoSet);
    }
}
