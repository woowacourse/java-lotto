package lotto.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class NumberGenerator {

    public static List<Integer> numberGeneratorWithUniqueValues(int makeQuantity, int minValue, int maxValue) {
        Set<Integer> set = new HashSet<>();
        do {
            int randomNum = generateRandomNumber(minValue, maxValue);
            set.add(randomNum);
        } while (set.size() != makeQuantity);
        List<Integer> lottoNumbers = new ArrayList<>(set);
        Collections.sort(lottoNumbers);
        return lottoNumbers;
    }

    private static int generateRandomNumber(int minValue, int maxValue) {
        Random random = new Random();
        int bound = maxValue - minValue + 1;
        return random.nextInt(bound) + minValue;
    }
}
