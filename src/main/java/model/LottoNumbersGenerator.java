package model;

import common.NumbersGenerator;
import java.util.List;
import java.util.Random;

public class LottoNumbersGenerator implements NumbersGenerator {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final int NUMBER_SIZE = 6;

    public List<Integer> generate() {
        return new Random()
                .ints(MIN_NUMBER, MAX_NUMBER + 1)
                .distinct()
                .limit(NUMBER_SIZE)
                .boxed()
                .toList();
    }
}
