package service;

import constant.LottoConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumberGenerator {

    public static final int LOTTO_FROM_INDEX = 0;

    private static LottoNumberGenerator instance;

    private final List<Integer> numbers;

    private LottoNumberGenerator() {
        numbers = new ArrayList<>();
        for (int num = LottoConstants.LOTTO_NUMBER_START; num <= LottoConstants.LOTTO_NUMBER_END; num++) {
            numbers.add(num);
        }
    }

    public static LottoNumberGenerator getInstance() {
        if (instance == null) {
            instance = new LottoNumberGenerator();
        }
        return instance;
    }

    public List<Integer> generateLottoNumbers() {
        List<Integer> tmp = new ArrayList<>(numbers);
        Collections.shuffle(tmp);
        return tmp.subList(LOTTO_FROM_INDEX, LottoConstants.LOTTO_COUNT);
    }
}
