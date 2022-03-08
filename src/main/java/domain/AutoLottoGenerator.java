package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AutoLottoGenerator implements LottoGenerator {

    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int MIN_RANGE = 0;
    private static final int MAX_RANGE = 6;

    @Override
    public List<LottoNumber> generateLottoNumber() {
        List<Integer> lottoNumberCandidates = IntStream.range(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER + 1)
                .boxed()
                .collect(Collectors.toList());
        return pickNumbersFromCandidates(lottoNumberCandidates);
    }

    private List<LottoNumber> pickNumbersFromCandidates(List<Integer> cadidates) {
        Collections.shuffle(cadidates);
        return cadidates.subList(MIN_RANGE, MAX_RANGE)
                .stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList());
    }
}
