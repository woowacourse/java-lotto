package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumberGenerator {

    private static final int MINIMUM_RANGE = 1;
    private static final int MAXIMUM_RANGE = 45;

    private final List<LottoNumber> lottoNumbers;

    public LottoNumberGenerator() {
        lottoNumbers = createLottoNumbers();
    }

    private List<LottoNumber> createLottoNumbers() {
        return IntStream.rangeClosed(MINIMUM_RANGE, MAXIMUM_RANGE)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
    }

    private void shuffleNumbers() {
        Collections.shuffle(lottoNumbers);
    }

    public List<LottoNumber> getLottoNumbers(int count) {
        shuffleNumbers();
        List<LottoNumber> numbers = lottoNumbers.subList(0, count);
        Collections.sort(numbers);
        return new ArrayList<>(numbers);
    }
}
