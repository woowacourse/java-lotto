package lotto.domain.lottogenerator;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.lotto.LottoNumber;

public class RandomLottoGenerator implements LottoGenerator {

    private List<Integer> lottoAllNumbers;

    public RandomLottoGenerator() {
        lottoAllNumbers = IntStream
            .range(LottoNumber.MIN + 1, LottoNumber.MAX + 1)
            .boxed()
            .collect(Collectors.toList());
    }

    @Override
    public List<LottoNumber> generateLottoNumbers() {
        List<LottoNumber> lottoNumbers = getShuffledLottoAllNumbers()
            .stream()
            .map(LottoNumber::valueOf)
            .limit(6)
            .collect(Collectors.toList());
        lottoNumbers.sort((n1, n2) -> Integer.compare(n1.getValue(), n2.getValue()));
        return lottoNumbers;
    }

    private List<Integer> getShuffledLottoAllNumbers() {
        Collections.shuffle(lottoAllNumbers);
        return lottoAllNumbers;
    }
}
