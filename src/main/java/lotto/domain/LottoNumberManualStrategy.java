package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoNumberManualStrategy implements LottoNumberStrategy {

    private final List<List<LottoNumber>> lottoNumbers;

    private LottoNumberManualStrategy(List<List<LottoNumber>> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public static LottoNumberManualStrategy generateManualLottoNumbers(List<List<Integer>> inputLottoNumbers) {
        return new LottoNumberManualStrategy(createLottoNumbers(inputLottoNumbers));
    }

    private static List<List<LottoNumber>> createLottoNumbers(List<List<Integer>> inputLottoNumbers) {
        return inputLottoNumbers.stream()
                .map(number -> number.stream()
                        .map(LottoNumber::valueOf)
                        .sorted(LottoNumber::compareTo)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    @Override
    public List<List<LottoNumber>> getLottoNumbers() {
        return new ArrayList<>(lottoNumbers);
    }
}
