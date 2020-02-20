package lotto.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomGenerator {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int LOTTO_NUMBER_SIZE = 6;
    private Random random;
    private List<Integer> totalRandomNumbers;

    public RandomGenerator() {
        this.random = new Random();
        this.totalRandomNumbers = new ArrayList<>();
        for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            this.totalRandomNumbers.add(i);
        }
    }

    public List<Integer> getRandomNumbers(){
        Collections.shuffle(this.totalRandomNumbers);
        List<Integer> randomNumbers = new ArrayList<>();
        for (int i = 0; i < LOTTO_NUMBER_SIZE; i++) {
            randomNumbers.add(totalRandomNumbers.get(i));
        }
        return randomNumbers;
    }
}
