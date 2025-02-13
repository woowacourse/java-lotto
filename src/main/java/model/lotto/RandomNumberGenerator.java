package model.lotto;

import static constant.NumberConstants.LOTTO_NUMBER_END;
import static constant.NumberConstants.LOTTO_NUMBER_START;

import constant.NumberConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomNumberGenerator {
    private static final List<Integer> numbers = IntStream.rangeClosed(LOTTO_NUMBER_START, LOTTO_NUMBER_END)
            .boxed()
            .collect(Collectors.toList());

    public List<Integer> generateLottoNumbers() {
        List<Integer> shuffledNumbers = new ArrayList<>(numbers);
        Collections.shuffle(shuffledNumbers);
        return shuffledNumbers.subList(0, NumberConstants.LOTTO_COUNT);
    }
}