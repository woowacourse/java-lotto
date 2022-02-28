package lotto.model.generator;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.model.number.LottoNumber;
import lotto.model.number.LottoNumbers;

public class AutoLottoNumbersGenerator implements LottoGenerator {

    private static final int LOTTO_START_INDEX = 0;

    public LottoNumbers generateLottoNumbers(int minimumNumber, int maximumNumber, int lottoLength)
            throws RuntimeException {
        List<Integer> numberCollection = makeNumberCollection(minimumNumber, maximumNumber);
        Collections.shuffle(numberCollection);
        return new LottoNumbers(numberCollection.subList(LOTTO_START_INDEX, lottoLength).stream()
                .sorted()
                .map(LottoNumber::new)
                .collect(Collectors.toList()));
    }

    private List<Integer> makeNumberCollection(int minimumNumber, int maximumNumber) {
        return IntStream.rangeClosed(minimumNumber, maximumNumber)
                .boxed()
                .collect(Collectors.toList());
    }
}
