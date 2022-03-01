package lotto.utils;

import static lotto.domain.Lotto.LOTTO_SIZE;
import static lotto.domain.LottoNumber.LOTTO_MAXIMUM;
import static lotto.domain.LottoNumber.LOTTO_MINIMUM;

import java.util.Collections;
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

    public static List<LottoNumber> generateRandomLottoNumbers() {
        Collections.shuffle(originLottoNumbers);
        return originLottoNumbers.subList(0, LOTTO_SIZE).stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public static List<LottoNumber> generateManualLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }
}
