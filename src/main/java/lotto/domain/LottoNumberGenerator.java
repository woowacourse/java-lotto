package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumberGenerator {
    public static final int MINIMUM_CANDIDATE_NUMBER = 1;
    public static final int MAXIMUM_CANDIDATE_NUMBER = 45;
    private static final int START_INDEX = 0;
    private static final int END_INDEX = 6;

    public LottoNumberGenerator() {
    }

    public List<Integer> make() {
        List<Integer> numbers = createCandidates();
        Collections.shuffle(numbers);
        List<Integer> lottoNumber = numbers.subList(START_INDEX, END_INDEX);
        Collections.sort(lottoNumber);

        return lottoNumber;
    }

    private List<Integer> createCandidates() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = MINIMUM_CANDIDATE_NUMBER; i <= MAXIMUM_CANDIDATE_NUMBER; i++) {
            numbers.add(i);
        }
        return numbers;
    }
}
