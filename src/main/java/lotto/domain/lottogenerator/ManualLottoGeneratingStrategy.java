package lotto.domain.lottogenerator;

import lotto.domain.LottoNumber;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ManualLottoGeneratingStrategy implements LottoGeneratingStrategy {
    private List<Integer> inputLottoNumbers;

    public ManualLottoGeneratingStrategy(List<Integer> inputLottoNumbers) {
        this.inputLottoNumbers = inputLottoNumbers;
    }

    @Override
    public List<LottoNumber> generate() {
        List<LottoNumber> lottoNumbers = inputLottoNumbers.stream()
                .sorted()
                .map(LottoNumber::getNumber)
                .collect(Collectors.toList());

        return Collections.unmodifiableList(lottoNumbers);
    }
}
