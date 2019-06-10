package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

public class ManualLottoNumbersGenerator implements LottoNumbersGenerator {
    private final List<Integer> lottoNumbers;

    public ManualLottoNumbersGenerator(List<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    @Override
    public List<LottoNumber> generateNumbers() {
        return lottoNumbers.stream().map(n -> LottoNumber.valueOf(n)).collect(Collectors.toList());
    }
}
