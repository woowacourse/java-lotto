package Lotto.domain;

import Lotto.utils.AutoLottoNumberGenerator;
import Lotto.utils.ManualLottoNumberGenerator;

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
        if (manualInputs == null) return null;
        return new Lottos(manualInputs.stream()
                .map(LottoFactory::generateManualSingleLotto)
                .collect(Collectors.toList()));
    }

    private static Lotto generateManualSingleLotto(String manualInput) {
        return new Lotto(manualLottoNumberGenerator.generate(manualInput));
    }

    public static Lottos generateAutoLottos(LottoAmount amount) {
        return new Lottos(IntStream.range(0, amount.getLottoAmount())
                .mapToObj(t -> generateAutoSingleLotto())
                .collect(Collectors.toList()));
    }

    private static Lotto generateAutoSingleLotto() {
        return new Lotto(autoLottoNumberGenerator.generate(NULL));
    }

    public static Lottos concatLottos(Lottos autoLottos, Lottos manualLottos) {
        List<Lotto> allLotto = autoLottos.getLottos();
        if (manualLottos != null) {
            allLotto.addAll(manualLottos.getLottos());
        }
        return new Lottos(allLotto);
    }
}

