package service;

import constant.LottoConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumberGenerator {

    public static final int LOTTO_FROM_INDEX = 0;

    private final List<Integer> numbers;

    public LottoNumberGenerator() {
        numbers = new ArrayList<>();
        for (int num = LottoConstants.LOTTO_NUMBER_START; num <= LottoConstants.LOTTO_NUMBER_END; num++) {
            numbers.add(num);
        }
    }

    public List<Integer> generateLottoNumbers() {
        List<Integer> tmp = new ArrayList<>(numbers);
        Collections.shuffle(tmp);
        return tmp.subList(LOTTO_FROM_INDEX, LottoConstants.LOTTO_COUNT);
    }
}
