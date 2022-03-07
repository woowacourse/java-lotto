package lotto.generator;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;

public class LottoGenerator {

    private static final int LOTTO_SIZE = 6;

    public static Lotto generateLottoByManual(List<Integer> numbers) {
        return new Lotto(convertToLottoNumbers(numbers));
    }

    public static Lotto generateLottoByAuto() {
        return new Lotto(getRandomLottoNumbers());
    }

    private static List<LottoNumber> convertToLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::from)
                .sorted()
                .collect(Collectors.toList());
    }

    private static List<LottoNumber> getRandomLottoNumbers() {
        List<LottoNumber> lottoNumbers = LottoNumber.getLottoNumbers();
        Collections.shuffle(lottoNumbers);
        return lottoNumbers.stream()
                .limit(LOTTO_SIZE)
                .sorted()
                .collect(Collectors.toList());
    }
}
