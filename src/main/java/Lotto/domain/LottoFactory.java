package Lotto.domain;

import Lotto.utils.AutoLottoNumberGenerator;
import Lotto.utils.ManualLottoNumberGenerator;
import Lotto.utils.NumberParser;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoFactory {
    private static final AutoLottoNumberGenerator autoLottoNumberGenerator = new AutoLottoNumberGenerator();
    private static final ManualLottoNumberGenerator manualLottoNumberGenerator = new ManualLottoNumberGenerator();
    private static final String NULL = null;

    private LottoFactory() {
    }

    public static Lottos generateManualLottos(List<String> manualInputs) {
        return new Lottos(manualInputs.stream()
                .map(LottoFactory::generateManualSingleLotto)
                .collect(Collectors.toList()));
    }

    private static Lotto generateManualSingleLotto(String manualInput) {
        return new Lotto(manualLottoNumberGenerator.generate(manualInput));
    }

    public static Lottos generateAutoLottos(int amount) {
        return new Lottos(IntStream.range(0, amount)
                .mapToObj(t -> generateAutoSingleLotto())
                .collect(Collectors.toList()));
    }

    private static Lotto generateAutoSingleLotto() {
        return new Lotto(autoLottoNumberGenerator.generate(NULL));
    }

    public static Lottos concatLottos(Lottos autoLottos, Lottos manualLottos) {
        List<Lotto> allLotto = autoLottos.getLottos();
        allLotto.addAll(manualLottos.getLottos());
        return new Lottos(allLotto);
    }
}

