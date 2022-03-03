package lotto.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.LottoNumber;

public class LottoGenerator {
    private static final List<LottoNumber> LOTTO_NUMBERS = new ArrayList<>(LottoNumber.getLottoNumbers());

    public static List<LottoNumber> getRandomLottoNumbers(int pickupCount) {
        Collections.shuffle(LOTTO_NUMBERS);
        return LOTTO_NUMBERS.stream()
                .limit(pickupCount)
                .sorted()
                .collect(Collectors.toList());
    }

    public static List<LottoNumber> convertToLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::getByNumber)
                .sorted()
                .collect(Collectors.toList());
    }
}
