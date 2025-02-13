package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumberGenerator {

    public static final int LOTTO_NUM_RANGE_START = 1;
    public static final int LOTTO_NUM_RANGE_END = 45;
    public static final int LOTTO_FROM_INDEX = 0;
    public static final int LOTTO_TO_INDEX = 6;

    private final List<Integer> numbers;

    public LottoNumberGenerator() {
        numbers = new ArrayList<>();
        for (int num = LOTTO_NUM_RANGE_START; num <= LOTTO_NUM_RANGE_END; num++) {
            numbers.add(num);
        }
    }

    public List<Integer> generateLottoNumbers() {
        List<Integer> tmp = new ArrayList<>(numbers);
        Collections.shuffle(tmp);
        return tmp.subList(LOTTO_FROM_INDEX, LOTTO_TO_INDEX);
    }
}
