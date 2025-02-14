package model;

import common.NumberGenerator;
import java.util.List;

public class LottoNumberGenerator {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final int NUMBER_SIZE = 6;

    public List<Integer> generate() {
        return NumberGenerator.generate(NUMBER_SIZE, MIN_NUMBER, MAX_NUMBER);
    }
}
