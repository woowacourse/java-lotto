package lotto.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;

public class LottoGenerator {

    private static final List<LottoNumber> LOTTO_NUMBERS = new ArrayList<>(LottoNumber.getLottoNumbers());
    private static final int LOTTO_SIZE = 6;

    public static Lotto generateLottoByManual(List<Integer> numbers) {
        return new Lotto(convertToLottoNumbers(numbers));
    }

    public static Lotto generateLottoByAuto() {
        return new Lotto(getRandomLottoNumbers());
    }

    private static List<LottoNumber> convertToLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::getByNumber)
                .sorted()
                .collect(Collectors.toList());
    }

    private static List<LottoNumber> getRandomLottoNumbers() {
        Collections.shuffle(LOTTO_NUMBERS);
        return LOTTO_NUMBERS.stream()
                .limit(LOTTO_SIZE)
                .sorted()
                .collect(Collectors.toList());
    }
}
