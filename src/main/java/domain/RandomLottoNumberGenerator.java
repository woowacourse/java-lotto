package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoNumberGenerator implements LottoNumberGenerator {

    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 46;
    private static final int MIN_RANGE = 0;
    private static final int MAX_RANGE = 6;

    @Override
    public List<Integer> generateLottoNumbers() {
        List<Integer> lottoNumberCandidates = IntStream.range(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                .boxed().collect(Collectors.toList());
        return pickLottoNumbersFromCandidates(lottoNumberCandidates);
    }

    private List<Integer> pickLottoNumbersFromCandidates(List<Integer> cadidates) {
        Collections.shuffle(cadidates);
        return cadidates.subList(MIN_RANGE, MAX_RANGE);
    }
}
