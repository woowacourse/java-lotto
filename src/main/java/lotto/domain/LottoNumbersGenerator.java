package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumbersGenerator {
    private static final int LOTTO_MAX_NUMBER = 45;
    private static final int LOTTO_MIN_NUMBER = 1;
    static final int LOTTO_NUMBER_SIZE = 6;
    private static final int ONE = 1;
    private static final List<Integer> LOTTO_NUMBERS_BOX = IntStream.range(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER + ONE)
        .boxed()
        .collect(Collectors.toList());

    static List<List<Integer>> generate(int lottosSize) {
        List<List<Integer>> lottoNumbers = new ArrayList<>(new ArrayList<>());
        for (int i = 0; i < lottosSize; i++ ) {
            List<Integer> lottoNumber = generateLottoNumbers();
            if (!lottoNumbers.contains(lottoNumber)) {
                lottoNumbers.add(lottoNumber);
            }
        }
        return lottoNumbers;
    }

    private static List<Integer> generateLottoNumbers() {
        Collections.shuffle(LOTTO_NUMBERS_BOX);
        return new ArrayList<>(
            Collections.unmodifiableCollection(LOTTO_NUMBERS_BOX.subList(ONE, ONE + LOTTO_NUMBER_SIZE)));
    }

    private static boolean isDuplicated(List<Integer> lottoNumbers, int lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

}
