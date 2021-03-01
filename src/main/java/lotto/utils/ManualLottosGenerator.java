package lotto.utils;

import lotto.domain.Lotto;
import lotto.domain.LottoNumbers;
import lotto.domain.Lottos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ManualLottosGenerator implements LottosGenerator {

    private final List<LottoNumbers> lottoNumbers = new ArrayList<>();

    public ManualLottosGenerator(List<String> manualNumbers) throws IllegalArgumentException {
        for (String numbers : manualNumbers) {
            lottoNumbers.add(LottoNumbersFactory.createLottoNumbers(numbers));
        }
    }

    @Override
    public Lottos generate() throws IllegalArgumentException {
        List<Lotto> manualLottos = lottoNumbers.stream()
                .map(Lotto::new)
                .collect(Collectors.toList());
        return new Lottos(manualLottos);
    }
}