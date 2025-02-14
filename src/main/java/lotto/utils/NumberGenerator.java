package lotto.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class NumberGenerator {

    public static List<Integer> numberGeneratorWithUniqueValues(int makeQuantity, int minValue, int maxValue) {
        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        do {
            int randomNum = random.nextInt(maxValue - minValue + 1) + minValue;
            set.add(randomNum);
        } while (set.size() != makeQuantity);

        List<Integer> lottoNumbers = new ArrayList<>(set);
        Collections.sort(lottoNumbers);
        return lottoNumbers;
    }

}
