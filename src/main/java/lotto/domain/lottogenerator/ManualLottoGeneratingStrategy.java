package lotto.domain.lottogenerator;

import lotto.domain.lotto.LottoNumber;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ManualLottoGeneratingStrategy implements LottoGeneratingStrategy {
    private final List<Integer> inputLottoNumbers;

    public ManualLottoGeneratingStrategy(List<Integer> inputLottoNumbers) {
        if (Objects.isNull(inputLottoNumbers) || inputLottoNumbers.isEmpty()) {
            throw new NullPointerException();
        }
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
