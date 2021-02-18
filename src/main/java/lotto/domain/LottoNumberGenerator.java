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

    public List<LottoNumber> createLotto() {
        List<Integer> candidateNumbers = createCandidates();
        Collections.shuffle(candidateNumbers);
        List<Integer> numbers = candidateNumbers.subList(START_INDEX, END_INDEX);
        Collections.sort(numbers);

        return createLottoNumbers(numbers);
    }

    private List<LottoNumber> createLottoNumbers(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (Integer number : numbers) {
            lottoNumbers.add(new LottoNumber(number));
        }
        return lottoNumbers;
    }

    private List<Integer> createCandidates() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = MINIMUM_CANDIDATE_NUMBER; i <= MAXIMUM_CANDIDATE_NUMBER; i++) {
            numbers.add(i);
        }
        return numbers;
    }
}
