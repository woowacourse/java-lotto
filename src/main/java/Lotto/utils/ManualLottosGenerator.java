package Lotto.utils;

import Lotto.domain.Lotto;
import Lotto.domain.Lottos;

import java.util.List;
import java.util.stream.Collectors;

public class ManualLottosGenerator implements LottosGenerator {
    private List<String> manualInputs;

    public ManualLottosGenerator(List<String> manualInputs) {
        this.manualInputs = manualInputs;
    }

    @Override
    public Lottos generate() {
        if (manualInputs == null) return null;
        return new Lottos(manualInputs.stream()
                .map(t -> new Lotto(NumberParser.parseIntoLottoNumbers(t)))
                .collect(Collectors.toList()));
    }
}
