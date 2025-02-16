package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumberGenerator implements NumberGenerate {

    public static final int LOTTO_FROM_INDEX = 0;

    private static LottoNumberGenerator instance;

    private LottoNumberGenerator() {
    }

    public static LottoNumberGenerator getInstance() {
        if (instance == null) {
            instance = new LottoNumberGenerator();
        }
        return instance;
    }

    @Override
    public List<Integer> generateRandomInRange(int start, int end, int size) {
        ArrayList<Integer> numbersOfRange = IntStream.rangeClosed(start, end)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));

        Collections.shuffle(numbersOfRange);
        return numbersOfRange.subList(LOTTO_FROM_INDEX, size);
    }
}
