package lotto.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import lotto.domain.LottoNumber;

public class LottoGenerator {

    private static final int LOTTO_MINIMUM = 1;
    private static final int LOTTO_MAXIMUM = 45;
    private static final int FROM_LOTTO_INDEX = 0;
    private static final int TO_LOTTO_INDEX = 6;

    private static final List<LottoNumber> originLottoNumbers = new ArrayList<>();

    static {
        IntStream.rangeClosed(LOTTO_MINIMUM, LOTTO_MAXIMUM)
                .forEach(number -> originLottoNumbers.add(new LottoNumber(number)));
    }

    private LottoGenerator() {
    }

    public static List<LottoNumber> generateLottoNumbers() {
        Collections.shuffle(originLottoNumbers);
        List<LottoNumber> lottoNumbers = originLottoNumbers.subList(FROM_LOTTO_INDEX, TO_LOTTO_INDEX);
        Collections.sort(lottoNumbers);

        return lottoNumbers;
    }
}
