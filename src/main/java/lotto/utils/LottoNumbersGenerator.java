package lotto.utils;

import static lotto.domain.LottoNumber.LOTTO_MAXIMUM;
import static lotto.domain.LottoNumber.LOTTO_MINIMUM;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.LottoNumber;

public class LottoNumbersGenerator {

    private static final List<LottoNumber> originLottoNumbers;

    static {
        originLottoNumbers = IntStream.rangeClosed(LOTTO_MINIMUM, LOTTO_MAXIMUM)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
    }

    private LottoNumbersGenerator() {
    }

    public static List<LottoNumber> getOriginLottoNumbers() {
        return new ArrayList<>(originLottoNumbers);
    }
}
