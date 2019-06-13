package lotto.domain.generator;

import lotto.domain.Lotto;
import lotto.domain.LottosGenerator;

import java.util.ArrayList;
import java.util.List;

import static lotto.domain.generator.LottoNumbersGenerator.generateLottoNumbers;

public class LottosManualGenerator implements LottosGenerator {
    private final List<String> inputLottos;

    public LottosManualGenerator(List<String> inputLottos) {
        this.inputLottos = inputLottos;
    }

    private static List<Lotto> generateManualLottos(List<String> inputLottos) {
        List<Lotto> lottos = new ArrayList<>();
        for (String inputLottoNumbers : inputLottos) {
            lottos.add(generateManualLotto(inputLottoNumbers));
        }
        return lottos;
    }

    public static Lotto generateManualLotto(String inputLottoNumbers) {
        return new Lotto(generateLottoNumbers(inputLottoNumbers));
    }

    @Override
    public List<Lotto> generate() {
        return generateManualLottos(inputLottos);
    }
}
