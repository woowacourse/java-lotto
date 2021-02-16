package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumber {
    private static final int START_INDEX = 0;
    private static final int END_INDEX = 6;
    private static final int MAXIMUM_CANDIDATE_NUMBER = 45;

    public LottoNumber(){
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
        for(int i = 1; i <= MAXIMUM_CANDIDATE_NUMBER; i++) {
            numbers.add(i);
        }
        return numbers;
    }
}
