package lotto.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.LottoNumber;

public class LottoGenerator {

    private static final int LOTTO_MINIMUM = 1;
    private static final int LOTTO_MAXIMUM = 45;
    private static final int FROM_LOTTO_INDEX = 0;
    private static final int TO_LOTTO_INDEX = 6;

    private static final List<LottoNumber> INIT_LOTTO_NUMBERS = IntStream.rangeClosed(LOTTO_MINIMUM, LOTTO_MAXIMUM)
            .mapToObj(LottoNumber::new)
            .collect(Collectors.toUnmodifiableList());

    private LottoGenerator() {
    }

    public static List<LottoNumber> generateLottoNumbers() {
        List<LottoNumber> numbers = new ArrayList<>(INIT_LOTTO_NUMBERS);
        Collections.shuffle(numbers);
        List<LottoNumber> lottoNumbers = numbers.subList(FROM_LOTTO_INDEX, TO_LOTTO_INDEX);
        Collections.sort(lottoNumbers);

        return lottoNumbers;
    }
}
