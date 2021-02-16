package lotto.lottoticket;

import util.RandomUtil;

import java.util.ArrayList;
import java.util.List;

public class RandomNumbersGenerator implements NumbersGenerator {
    public static final int MINIMUM_NUMBER = 1;
    public static final int MAXIMUM_NUMBER = 45;
    public static final int NUMBER_COUNT_IN_LOTTO = 6;

    @Override
    public List<Integer> generate() {
        List<Integer> randomNumbers = new ArrayList<>();
        for (int i = 0; i < NUMBER_COUNT_IN_LOTTO; i++){
            randomNumbers.add(RandomUtil.nextInt(MINIMUM_NUMBER, MAXIMUM_NUMBER));
        }
        return randomNumbers;
    }
}
