package lotto.util;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.model.LottoNumber;
import lotto.model.LottoNumbers;

public class LottoNumbersGenerator implements LottoUtil {

    private static final int LOTTO_START_INDEX = 0;
    private static final int LOTTO_LAST_INDEX = 6;

    private static List<Integer> numberCollection;

    public LottoNumbers generateLottoNumbers(int minimumNumber, int maximumNumber) {
        numberCollection = IntStream.rangeClosed(minimumNumber, maximumNumber)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(numberCollection);
        return new LottoNumbers(numberCollection.subList(LOTTO_START_INDEX, LOTTO_LAST_INDEX).stream()
                .sorted()
                .map(LottoNumber::new)
                .collect(Collectors.toList()));
    }
}
